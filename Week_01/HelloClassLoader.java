package demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;

public class HelloClassLoader extends ClassLoader {

    private static final String PATH = "E:\\workspace\\app\\src\\demo\\Hello.xlass";

    public static void main(String[] args) {
        try {
            HelloClassLoader loader = new HelloClassLoader();
            Object obj = loader.findClass(PATH).newInstance();
            Method method = obj.getClass().getMethod("hello");
            method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        try {
            File file = new File(name);
            byte[] bytes = Files.readAllBytes(file.toPath());
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (255 - bytes[i]);
            }
            return defineClass("Hello", bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
