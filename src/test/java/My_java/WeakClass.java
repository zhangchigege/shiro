package My_java;/**
 * @program: SpringCloud_Parent
 * @description: Cglib
 * @author: ZhangChi
 * @create: 2019-08-26 17:20
 **/

import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 * @program: SpringCloud_Parent
 *
 * @description: Cglib
 *
 * @author: ZhangChi
 *
 * @create: 2019-08-26 17:20
 **/
public class WeakClass {

    public WeakClass() {

    }

    public void method() {
        System.out.println("I am a weak class's method !");
    }

    public void filterMethod() {
        System.out.println("WeakClass.filterMethod()");
    }

    interface IfaceA {
        void doA();
    }
    interface IfaceB {
        void doB();
    }

    static class IfaceAImpl implements IfaceA {

        @Override
        public void doA() {
            System.out.println("IfaceAImpl.doA()");
        }
    }

    static class IfaceBImpl implements IfaceB {

        @Override
        public void doB() {
            System.out.println("IfaceAImpl.doB()");
        }
    }

    /**
     * 代理增强方法
     */
    static class MethodInerceptorImpl implements MethodInterceptor {

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("intercept -> " + method + ":");
            methodProxy.invokeSuper(o, objects);
            return null;
        }
    }

    /**
     * 对方法进行拦截
     */
    static class CallbackFilterImpl implements CallbackFilter {

        @Override
        public int accept(Method method) {
            if (method.getName().equals("method")) {
                return 1;
            }else {
                return 0;
            }
        }
    }



    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(WeakClass.class);
        WeakClass weakClass;
        enhancer.setCallback(new MethodInerceptorImpl());
        weakClass = (WeakClass)enhancer.create();
        weakClass.method();



        Callback[] callbacks = new Callback[]{new MethodInerceptorImpl(), NoOp.INSTANCE};
        enhancer.setCallbacks(callbacks);
        enhancer.setCallbackFilter(new CallbackFilterImpl());
        weakClass = (WeakClass) enhancer.create();
        weakClass.method();
        weakClass.filterMethod();


        Class[] classes = new Class[]{IfaceA.class, IfaceB.class};

        Object[] delegates = new Object[]{new IfaceAImpl(), new IfaceBImpl()};

        Object obj = Mixin.create(classes, delegates);

        IfaceA ifaceA = (IfaceA) obj;
        IfaceB ifaceB = (IfaceB) obj;

        ifaceA.doA();
        ifaceB.doB();



    }

}
