package com.github.book.io;

import com.github.book.common.Constants;
import com.github.book.meta.BookContentInfo;
import com.github.book.meta.FileNoteInfo;
import com.github.book.meta.FileNoteItem;
import com.github.book.meta.ManifestItem;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookConverter {

    public void convert(BookContentInfo content) throws IOException{
        System.out.println("开始转换:"+content.getMetadata().getTitle());

        for(ManifestItem item : content.getManifest()){

            if(!item.isHyperText()){
                continue;
            }

            String href = item.getHref();

            String path = StringUtils.join(new String[]{content.getContentPath(), File.separator,href});

            Document document = Jsoup.parse(new File(path), "utf-8");
            FileNoteInfo note = getNotes(document);

            if(note != null) {
                System.out.println("文档" + path + "发现" + note.getNotes().size() + "个注释！");
                addImageAndStyles(document,content,note);

                Element ol = document.createElement("ol");
                ol.attr("duokan-footnote-content");
                for(FileNoteItem noteItem : note.getNotes()){
                    addImageNote(document,ol,content,note,noteItem);
                }
                document.selectFirst("body").appendChild(ol);

                try(OutputStream os = new FileOutputStream(note.getPath())) {
                    IOUtils.write(document.toString(), os,"UTF-8");
                }catch (IOException e){
                    e.printStackTrace();
                }
            }else{
                System.out.println("文档" + path +"不存在注释，跳过处理步骤！");
            }
        }
    }


    private FileNoteInfo getNotes(Document document) throws IOException{
        String file = document.location();
        Elements sups = document.select("sup");

        List<FileNoteItem> notes = new ArrayList<>();
        for(Element sup : sups){
            Element supParentLink = sup.parent();
            if(supParentLink==null || !supParentLink.tagName().equalsIgnoreCase("a")){
                System.err.println("在文件"+file+"找到sup元素，但其父元素不存在或不是<a/>标签!");
                continue;
            }
            Element siblingLink = supParentLink.previousElementSibling();
            if(siblingLink == null || !siblingLink.tagName().equalsIgnoreCase("a")){
                System.err.println("在文件"+file+"找到sup元素，但其父元素兄弟节点不存在或不是<a/>标签!");
                continue;
            }

            FileNoteItem noteInfo = new FileNoteItem();
            noteInfo.setNoteId(siblingLink.attr("id"));
            noteInfo.setHref(supParentLink.attr("href"));
            noteInfo.setNoteText(sup.text());
            noteInfo.setNoteElement(supParentLink);

            String refId = noteInfo.getHref().split("#")[1];
            noteInfo.setReferenceId(refId);

            Element refNote = document.selectFirst("#" + refId);
            Element refNotePara = refNote.parent();
            if (refNotePara == null || !refNotePara.tagName().equalsIgnoreCase("p")) {
                System.err.println("在文件"+file+"找到注释"+refId+"，但其父元素节点不存在或不是<p/>标签!");
                continue;
            }
            Element refNoteLink= refNote.nextElementSibling();
            if (refNoteLink == null || !refNoteLink.tagName().equalsIgnoreCase("a")) {
                System.err.println("在文件"+file+"找到注释"+refId+"，但其兄弟节点不存在或不是<a/>标签!");
                continue;
            }
            noteInfo.setReferenceText(refNotePara.text());
            noteInfo.setReferenceHref(refNoteLink.attr("href"));
            noteInfo.setReferenceElement(refNotePara);

            notes.add(noteInfo);
        }

        if(notes.size()>0) {
            FileNoteInfo note = new FileNoteInfo();
            note.setPath(file);
            note.setNotes(notes);
            return note;
        }else{
            return null;
        }
    }

    public void  addImageNote(Document document,Element ol,BookContentInfo content,FileNoteInfo note,FileNoteItem item){
        Element link = document.createElement("a");
        Element img = document.createElement("img");
        link.appendChild(img);

        link.attr("class", "duokan-footnote");
        link.attr("href", item.getHref());
        link.attr("id", item.getNoteId());

        img.attr("alt", "注释");
        img.attr("class", "duokan-footnote");
        if(content.getContentPath().equals(new File(note.getPath()).getParent())){
            img.attr("src", "Images/note.png");
        }else {
            img.attr("src", "../Images/note.png");
        }

        Element noteElement = item.getNoteElement();
        noteElement.after(link);
        noteElement.previousElementSibling().remove();
        noteElement.remove();

        Element li = document.createElement("li");
        Element p = document.createElement("p");
        Element a = document.createElement("a");
        p.appendChild(a);
        li.appendChild(p);

        li.attr("class","duokan-footnote-item");
        li.attr("id",item.getReferenceId());

        p.attr("class","footnote-text");

        a.attr("class","duokan-footnote-link");
        a.attr("href",item.getReferenceHref());
        a.text(item.getReferenceText());

        ol.appendChild(li);

        item.getReferenceElement().remove();
    }

    public void addImageAndStyles(Document document,BookContentInfo content,FileNoteInfo note) {
        String rootPath = content.getContentPath();
        File imagesPath = new File(StringUtils.join(new String[]{rootPath,File.separator, Constants.DIR_IMAGES}));
        if(!imagesPath.exists()){
            imagesPath.mkdirs();
        }

        try(InputStream is = this.getClass().getClassLoader().getResourceAsStream("note.png");
            OutputStream os = new FileOutputStream(new File(imagesPath.getPath()+File.separator+"note.png"))){
            IOUtils.copy(is,os);
        }catch (IOException e){
            e.printStackTrace();
        }

        File stylesPath = new File(StringUtils.join(new String[]{rootPath,File.separator, Constants.DIR_STYLES}));
        if(!stylesPath.exists()){
            stylesPath.mkdirs();
        }

        try(InputStream is = this.getClass().getClassLoader().getResourceAsStream("note.css");
            OutputStream os = new FileOutputStream(new File(stylesPath.getPath()+File.separator+"note.css"))){
            IOUtils.copy(is,os);
        }catch (IOException e){
            e.printStackTrace();
        }

        Element head = document.selectFirst("head");

        Element link = document.createElement("link");

        String path1 = new File(note.getPath()).getParent();
        if(rootPath.equalsIgnoreCase(path1)) {
            link.attr("href", "Styles/note.css");
        }else{
            link.attr("href", "../Styles/note.css");
        }
        link.attr("rel", "stylesheet");
        link.attr("type", "text/css");
        head.appendChild(link);

    }
}
