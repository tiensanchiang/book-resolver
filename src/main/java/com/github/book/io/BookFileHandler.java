package com.github.book.io;


import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;

public class BookFileHandler {

    private String path;

    public BookFileHandler(String path) {
        this.path = path;
    }

    public String read() throws Exception {
        File file = new File(path);
        String userDir = System.getProperty("user.dir");
        String rootDir = StringUtils.join(new String[]{userDir, File.separator,
                "data", File.separator, file.getName(), File.separator});

        File root = new File(rootDir);
        if (root.exists()) {
            FileUtils.deleteDirectory(root);
        } else {
            root.mkdirs();
        }

        ZipUtil.unzip(path, rootDir);

        return rootDir;

    }

    public void create(){

    }

}
