package com.github.book.conf;

public class NoteElement {
    public String tagName;
    private String relation;
    private String attributes;
    private boolean textual;
    private boolean removable;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public boolean isTextual() {
        return textual;
    }

    public void setTextual(boolean textual) {
        this.textual = textual;
    }

    public boolean isRemovable() {
        return removable;
    }

    public void setRemovable(boolean removable) {
        this.removable = removable;
    }
}
