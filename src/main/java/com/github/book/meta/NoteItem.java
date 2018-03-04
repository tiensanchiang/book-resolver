package com.github.book.meta;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

public class FileNoteItem {

    private List<Element> elements;
    private String noteId;
    private String referencedId;
    private String noteText;
    private String noteHref;

    private FileNoteItem footNote;

    public FileNoteItem() {
        elements = new ArrayList<>();
    }

    public FileNoteItem(String noteId, String noteText, String noteHref) {
        this();
        this.noteId = noteId;
        this.noteText = noteText;
        this.noteHref = noteHref;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public String getNoteHref() {
        return noteHref;
    }

    public void setNoteHref(String noteHref) {
        this.noteHref = noteHref;
    }

    public FileNoteItem getFootNote() {
        return footNote;
    }

    public void setFootNote(FileNoteItem footNote) {
        this.footNote = footNote;
    }

    public String getReferencedId() {
        return referencedId;
    }

    public void setReferencedId(String referencedId) {
        this.referencedId = referencedId;
    }

    public void addNoteElement(Element element){
        elements.add(element);
    }

    public void remove(){
        if(elements != null ){
            for(Element element : elements){
                element.remove();
            }
        }
    }
}
