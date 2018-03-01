package com.github.book.io;

import com.github.book.meta.BookContentInfo;
import com.github.book.meta.BookMetaInfo;
import com.github.book.meta.ManifestItem;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BookContentResolver {

    private String contentFile;

    public BookContentResolver(String contentFile) {
        this.contentFile = contentFile;
    }

    public BookContentInfo resolve() throws DocumentException {

        BookContentInfo content = new BookContentInfo();
        File contentFile = new File(this.contentFile);

        SAXReader reader = new SAXReader();
        Document document = reader.read(contentFile);
        Element root = document.getRootElement();

        BookMetaInfo metaInfo = new BookMetaInfo();
        Element metadata = root.element("metadata");
        metaInfo.setCreator(getElementText(metadata.element("creator")));
        metaInfo.setIdentifier(getElementText(metadata.element("identifier")));
        metaInfo.setLanguage(getElementText(metadata.element("language")));
        metaInfo.setPublisher(getElementText(metadata.element("publisher")));
        metaInfo.setTitle(getElementText(metadata.element("title")));
        content.setMetadata(metaInfo);

        List<ManifestItem> manifestItems = new ArrayList<ManifestItem>();
        Element manifest = root.element("manifest");
        List<Element> items = manifest.elements("item");
        for(Element e : items){
            ManifestItem item = new ManifestItem();
            item.setId(getElementAttr(e,"id"));
            item.setHref(getElementAttr(e,"href"));
            item.setMediaType(getElementAttr(e,"media-type"));
            manifestItems.add(item);
        }
        content.setManifest(manifestItems);

        content.setContentPath(contentFile.getParent());
        return content;
    }


    private String getElementText(Element e){
        if(e==null)
            return null;
        return e.getText();
    }

    private String getElementAttr(Element e,String attr){
        if(e==null)
            return null;
        return e.attributeValue(attr);
    }
}
