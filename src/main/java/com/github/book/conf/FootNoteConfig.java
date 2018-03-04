package com.github.book.conf;

import java.util.List;

public class FootNote {
    private String id;
    private List<NoteElement> elements;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<NoteElement> getElements() {
        return elements;
    }

    public void setElements(List<NoteElement> elements) {
        this.elements = elements;
    }
}
