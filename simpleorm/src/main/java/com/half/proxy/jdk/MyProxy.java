package com.half.proxy.jdk;

import com.half.proxy.demo.MyInvocationHandler;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.stream.Stream;

/**
 * @Author: wangwei
 * @Date: 2020-04-13 8:49
 */
public class MyProxy {
    private static String ln = "\r\n";

    private static String comma = ",";
    private static String blank = " ";


    public static Object newProxyInstance(MyClassLoader loader,
                                          Class<?>[] interfaces,
                                          MyInvocationHandler h) {

        try {
            //1.生成源码 java文件
            String src = generateSrc(interfaces, h);

            String filePath = MyProxy.class.getResource("").getPath();
            File f = new File(filePath + "$MyProxy0.java");

            FileWriter fw = new FileWriter(f);
            fw.write(src);
            fw.flush();
            fw.close();

            //2.编译成class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
            Iterable iterable = manager.getJavaFileObjects(f);

            JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, iterable);
            task.call();
            manager.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        //3.加载class文件生成对象
        Class proxyClass = null;
        try {
            proxyClass = loader.findClass("$MyProxy0");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Constructor constructor = null;
        try {
            constructor = proxyClass.getConstructor(h.getClass());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            return constructor.newInstance(h);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    //构建java文件
    private static String generateSrc(Class<?>[] interfaces,
                                      InvocationHandler h) {
        StringBuilder sb = new StringBuilder();
        sb.append("package com.half.proxy.jdk;").append(ln);
        sb.append("import com.half.proxy.demo.MyInvocationHandler;").append(ln);
        sb.append("import java.lang.reflect.Method;").append(ln);
        sb.append("import java.lang.reflect.UndeclaredThrowableException;").append(ln);
        sb.append("public final class $MyProxy0  implements ")
                .append(getInterfaces(interfaces)).append("{").append(ln);
        //属性
        sb.append("MyInvocationHandler h;").append(ln);
        //构造方法
        sb.append("public $MyProxy0(MyInvocationHandler h){")
                .append("this.h=h;")
                .append("}").append(ln);
        //生成方法
        sb.append(getMethod(interfaces));
        sb.append("}");

        return sb.toString();
    }

    private static String getInterfaces(Class<?>[] interfaces) {
        StringBuilder sb = new StringBuilder();
        for (Class<?> inter : interfaces) {
            sb.append(inter.getName()).append(comma);
        }
        sb.delete(sb.length() - comma.length(), sb.length());
        return sb.toString();
    }

    private static String getMethod(Class<?>[] interfaces) {
        StringBuilder sb = new StringBuilder();
        for (Class<?> inter : interfaces) {
            for (Method method : inter.getDeclaredMethods()) {
                sb.append(getModifier(method)).append(blank)
                        .append(method.getReturnType()).append(blank)
                        .append(method.getName()).append("()").append("throws ").append(getException(method, comma)).append("{ ").append(ln);
                sb.append("try {").append(ln);
                sb.append("Method method=").append(inter.getName()).append(".class.getMethod(\"").append(method.getName()).append("\");").append(ln);
                sb.append(" h.invoke(this,method,null);").append(ln);
                sb.append("} catch ( ").append(getException(method, "|")).append(" | Error var2) {");
                sb.append("    throw var2; ");
                sb.append(" } catch (Throwable var3) {");
                sb.append("    throw new UndeclaredThrowableException(var3); }");

                sb.append("}").append(ln);
            }
        }
        sb.delete(sb.length() - comma.length(), sb.length());
        return sb.toString();

    }

    private static String getModifier(Method method) {
        int mod = method.getModifiers();
        String modStr = Modifier.isPublic(mod) ? "public" : "";
        //return Modifier.toString(modifiers);
        return modStr;

    }

    private static String getException(Method method, String fuhao) {
        StringBuilder sb = new StringBuilder();
        Class<?>[] clazzs = method.getExceptionTypes();
        Stream.of(clazzs).map(clazz -> clazz.getName()).forEach(name -> sb.append(name).append(fuhao));
        sb.delete(sb.length() - comma.length(), sb.length());
        return sb.toString();

    }

}
