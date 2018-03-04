package com.github.book.conf;

import java.util.List;

public class IndexNote {

    private String selector;
    private String expression;

    private List<NoteElement> elements;
    private FootNote footNote;

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public List<NoteElement> getElements() {
        return elements;
    }

    public void setElements(List<NoteElement> elements) {
        this.elements = elements;
    }

    public FootNote getFootNote() {
        return footNote;
    }

    public void setFootNote(FootNote footNote) {
        this.footNote = footNote;
    }
}
