package com.github.book.meta;

import java.util.List;

public class BookContentInfo {

    private String contentPath;

    private BookMetaInfo metadata;

    private List<ManifestItem> manifest;


    public BookMetaInfo getMetadata() {
        return metadata;
    }

    public String getContentPath() {
        return contentPath;
    }

    public void setContentPath(String contentPath) {
        this.contentPath = contentPath;
    }

    public void setMetadata(BookMetaInfo metadata) {
        this.metadata = metadata;
    }

    public List<ManifestItem> getManifest() {
        return manifest;
    }

    public void setManifest(List<ManifestItem> manifest) {
        this.manifest = manifest;
    }
}
