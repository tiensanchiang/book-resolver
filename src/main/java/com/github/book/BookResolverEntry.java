package com.github.book;

import com.github.book.io.BookContentResolver;
import com.github.book.io.BookConverter;
import com.github.book.io.BookFileHandler;
import com.github.book.meta.BookDescriptor;
import com.github.book.meta.OpenPublicationFormat;

public class BookResolverEntry {

    public static void main(String[] args) throws Exception {

        if (args == null || args.length < 1) {
            System.err.println("Usage: br  %Epub file name%");
            return;
        }

        BookFileHandler reader = new BookFileHandler(args[0]);
        String dir = reader.read();

//        String contentOpf = StringUtils.join(new String[]{dir,File.separator,
//                Constants.DIR_OEBPS,File.separator,"content.opf"});
//        if(!new File(contentOpf).exists()){
//            contentOpf = StringUtils.join(new String[]{dir,File.separator,"content.opf"});
//            if(!new File(contentOpf).exists())
//                throw new RuntimeException("opf文件不存在！");
//        }

        BookContentResolver bcr = new BookContentResolver(dir);
        BookDescriptor content = bcr.resolve();
        System.out.println(content);
//        BookConverter converter = new BookConverter();
//        converter.convert(content);
    }


}
