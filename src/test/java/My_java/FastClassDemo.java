package My_java;/**
 * @program: SpringCloud_Parent
 * @description:
 * @author: ZhangChi
 * @create: 2019-08-26 17:14
 **/

import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;

import java.lang.reflect.InvocationTargetException;

/**
 * @program: SpringCloud_Parent
 *
 * @description:
 *
 * @author: ZhangChi
 *
 * @create: 2019-08-26 17:14
 **/
public class FastClassDemo {
    public void m() {
        System.out.println("FastClass.m()");
    }

    public void m(int a) {
        System.out.println("FastClass.m(int)");
    }

    public void m(String s) {
        System.out.println("FastClass.m(String)");
    }

    public String m(int a, String s) {
        System.out.println("FastClass.m(int, String)");

        return s + "=" + a;
    }

    public static void main(String[] args) throws InvocationTargetException {
        FastClassDemo fastClassDemo = new FastClassDemo();

        FastClass fastClass = FastClass.create(FastClassDemo.class);

        FastMethod fastMethod;

        fastMethod = fastClass.getMethod("m", new Class[]{});
        fastMethod.invoke(fastClassDemo, new Object[]{});

        fastMethod = fastClass.getMethod("m", new Class[]{int.class});
        fastMethod.invoke(fastClassDemo, new Object[]{12});

        fastMethod = fastClass.getMethod("m", new Class[]{String.class});
        fastMethod.invoke(fastClassDemo, new Object[]{"hello cglib"});

        fastMethod = fastClass.getMethod("m", new Class[]{int.class, String.class});
        fastMethod.invoke(fastClassDemo, new Object[]{12, "hello cglib"});

    }

}

