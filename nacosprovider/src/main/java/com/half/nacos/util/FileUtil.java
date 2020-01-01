package com.half.nacos.util;

import java.io.File;
import java.util.stream.Stream;

/**
 * @Author: wangwei
 * @Date: 2019-12-21 11:41
 */
public class FileUtil {

    public static void main(String[] args) {
        String directory="E:\\迅雷下载\\电影\\爱丝小仙女思妍\\1-24";
        Stream.of(directory).map(a->new File(a)).flatMap(a->Stream.of(a.list())).forEach(a->move(a));

    }

    public static File move(String oldName){
        File OldFile=new File("E:\\迅雷下载\\电影\\爱丝小仙女思妍\\1-24\\"+oldName);
        File[] s=OldFile.listFiles();
        Stream.of(s).forEach(a->{
            a.renameTo(new File("E:\\迅雷下载\\电影\\爱丝小仙女思妍\\1-24\\"+a.getName()));
        });

        return OldFile;
    }
}
