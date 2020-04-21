package com.half.proxy.jdk;

import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;

/**
 * @Author: wangwei
 * @Date: 2020-04-13 9:04
 */
public class MyClassLoader extends ClassLoader {

    private File baseDir;

    public MyClassLoader() {
        String path = MyClassLoader.class.getResource("").getPath();
        baseDir = new File(path);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String packageName = MyClassLoader.class.getPackage().getName();
        File classFile = new File(baseDir, name.replace(".", "\\") + ".class");
        try {
            byte[] bytes = FileCopyUtils.copyToByteArray(classFile);
            return defineClass(packageName + "." + name, bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }
}
