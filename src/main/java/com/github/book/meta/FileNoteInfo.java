package com.github.book.meta;

import java.util.List;

public class FileNoteInfo {

   private String path;

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
}
