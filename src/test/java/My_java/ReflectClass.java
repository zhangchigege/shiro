package My_java;/**
 * @program: SpringCloud_Parent
 * @description:
 * @author: ZhangChi
 * @create: 2019-08-26 10:31
 **/

import org.apache.commons.logging.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @program: SpringCloud_Parent
 *
 * @description:
 *
 * @author: ZhangChi
 *
 * @create: 2019-08-26 10:31
 **/
public class ReflectClass {

    private final static String TAG = "My_java.ReflectClass";

    //创建对象
    public static void reflectNewInstance(){

        try {
            Class<?> book = Class.forName("My_java.Book");
            Object newInstance = book.newInstance();
            Book newBook = (Book) newInstance;
            newBook.setName("哥哥");
            newBook.setAuthor("张驰");
            System.out.println(newBook.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //反射所有的构造方法
    public static void reflectPrivateConstructor(){
        try {
            Class<?> clazz = Class.forName("My_java.Book");
            Constructor<?> constructor = clazz.getDeclaredConstructor(String.class, String.class);
            constructor.setAccessible(true);
            Object newInstance = constructor.newInstance("haha","hehe");
            Book book = (Book) newInstance;
            System.out.println(book.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //反射私有属性
    public static void reflectPrivateField(){
        try {
            Class<?> clazz = Class.forName("My_java.Book");
            Object newInstance = clazz.newInstance();
            Field field = clazz.getDeclaredField("TAG");
            field.setAccessible(true);
            String o = (String)field.get(newInstance);
            System.out.println(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //反射私有方法
    public static void reflectPrivateMethod(){
        try {
            Class<?> clazz = Class.forName("My_java.Book");
            Method method = clazz.getDeclaredMethod("declaredMethod", int.class);
            method.setAccessible(true);
            Object instance = clazz.newInstance();
            String string = (String) method.invoke(instance, 0);
            System.out.println(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ReflectClass.reflectNewInstance();
        ReflectClass.reflectPrivateMethod();
        ReflectClass.reflectPrivateField();
        ReflectClass.reflectPrivateConstructor();
    }


}
