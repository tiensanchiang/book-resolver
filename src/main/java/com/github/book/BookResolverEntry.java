package com.github.book;

import com.github.book.common.Constants;
import com.github.book.io.BookContentResolver;
import com.github.book.io.BookConverter;
import com.github.book.io.BookFileHandler;
import com.github.book.meta.BookContentInfo;
import org.apache.commons.lang.StringUtils;

public class BookResolverEntry {

    public static void main(String[] args) throws Exception {

        if (args == null || args.length < 1) {
            System.err.println("Usage: br  %Epub file name%");
            return;
        }

        BookFileHandler reader = new BookFileHandler(args[0]);
        String dir = reader.read();

        BookContentResolver bcr = new BookContentResolver(StringUtils.join(new String[]{
                dir, Constants.FILE_CONTENT}));
        BookContentInfo content = bcr.resolve();

        BookConverter converter = new BookConverter();
        converter.convert(content);



    }


}
