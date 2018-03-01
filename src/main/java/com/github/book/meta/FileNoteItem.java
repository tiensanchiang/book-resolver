package com.github.book.meta;

import org.jsoup.nodes.Element;

public class FileNoteItem {

    private String noteId;
    private String noteText;
    private String href;
    private Element noteElement;
    private String referenceId;
    private String referenceText;
    private String referenceHref;
    private Element referenceElement;

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

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getReferenceText() {
        return referenceText;
    }

    public void setReferenceText(String referenceText) {
        this.referenceText = referenceText;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getReferenceHref() {
        return referenceHref;
    }

    public void setReferenceHref(String referenceHref) {
        this.referenceHref = referenceHref;
    }

    public Element getNoteElement() {
        return noteElement;
    }

    public void setNoteElement(Element noteElement) {
        this.noteElement = noteElement;
    }

    public Element getReferenceElement() {
        return referenceElement;
    }

    public void setReferenceElement(Element referenceElement) {
        this.referenceElement = referenceElement;
    }

    @Override
    public String toString() {
        return "id:" + noteId + ",href:" + href + ",refId:" + referenceId + ",refHref:" + referenceHref;
    }
}
