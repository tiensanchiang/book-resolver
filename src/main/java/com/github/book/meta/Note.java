package com.github.book.meta;

import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

public class FileNoteInfo {

   private String path;

   private Document noteDocument;
   private Document footNoteDocument;

   private List<FileNoteItem> notes;

   public String getPath() {
      return path;
   }

   public void setPath(String path) {
      this.path = path;
   }

   public List<FileNoteItem> getNotes() {
      return notes;
   }

   public void setNotes(List<FileNoteItem> notes) {
      this.notes = notes;
   }

   public Document getNoteDocument() {
      return noteDocument;
   }

   public void setNoteDocument(Document noteDocument) {
      this.noteDocument = noteDocument;
   }

   public Document getFootNoteDocument() {
      return footNoteDocument;
   }

   public void setFootNoteDocument(Document footNoteDocument) {
      this.footNoteDocument = footNoteDocument;
   }

   public void addNote(FileNoteItem item){
        if(notes == null)
            notes = new ArrayList<>();
        notes.add(item);
   }

   public boolean hasNote(){
       return notes != null && notes.size()>0;
   }
}
