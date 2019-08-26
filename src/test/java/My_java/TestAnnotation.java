package My_java;/**
 * @program: SpringCloud_Parent
 * @description:
 * @author: ZhangChi
 * @create: 2019-08-26 11:12
 **/

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
 * @create: 2019-08-26 11:12
 **/
@MyAnnotation.MyClassAnnotation(desc = "The Class", uri = "My_java.MyAnnotation")
@MyAnnotation.MyClassAndMethodAnnotation(classType = MyAnnotation.MyClassAndMethodAnnotation.EnumType.util)
public class TestAnnotation {


    @MyAnnotation.MyFieldAnnotation(desc = "The Class Field", uri = "My_java.TestAnnotation#id")
    private String id;

    @MyAnnotation.MyConstructorAnnotation(desc = "The Class Constructor", uri = "My_java.TestAnnotation#constructor")
    public TestAnnotation() {
    }

    public String getId() {
        return id;
    }

    @MyAnnotation.MyMethodAnnotation(desc = "The Class Method", uri = "My_java.TestAnnotation#setId")
    public void setId(String id) {
        this.id = id;
    }

    @MyAnnotation.MyMethodAnnotation(desc = "The Class Method sayHello", uri = "My_java.TestAnnotation#sayHello")
    public void sayHello(String name) {
        if (name == null || name.equals("")) {
            System.out.println("hello world!");
        } else {
            System.out.println(name + "\t:say hello world");
        }
    }

    public static void main(String[] args) throws Exception {
        Class<TestAnnotation> clazz = TestAnnotation.class;
        // 获取类注解
        MyAnnotation.MyClassAnnotation myClassAnnotation = clazz.getAnnotation(MyAnnotation.MyClassAnnotation.class);
        System.out.println(myClassAnnotation.desc() + "+" + myClassAnnotation.uri());

        // 获得构造方法注解
        Constructor<TestAnnotation> constructors = clazz.getConstructor(new Class[] {});// 先获得构造方法对象
        MyAnnotation.MyConstructorAnnotation myConstructorAnnotation = constructors.getAnnotation(MyAnnotation.MyConstructorAnnotation.class);// 拿到构造方法上面的注解实例
        System.out.println(myConstructorAnnotation.desc() + "+" + myConstructorAnnotation.uri());

        // 获得方法注解
        Method method = clazz.getMethod("setId", new Class[] { String.class });// 获得方法对象
        MyAnnotation.MyMethodAnnotation myMethodAnnotation = method.getAnnotation(MyAnnotation.MyMethodAnnotation.class);
        System.out.println(myMethodAnnotation.desc() + "+" + myMethodAnnotation.uri());

        // 获得字段注解
        Field field = clazz.getDeclaredField("id");// 暴力获取private修饰的成员变量
        MyAnnotation.MyFieldAnnotation myFieldAnnotation = field.getAnnotation(MyAnnotation.MyFieldAnnotation.class);
        System.out.println(myFieldAnnotation.desc() + "+" + myFieldAnnotation.uri());
    }



}
