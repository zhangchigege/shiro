package My_java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: SpringCloud_Parent
 * @description: ${java动态代理}
 * @author: ZhangChi
 * @create: 2019-08-26 16:27
 **/
public class JavaDynamicProxyDemo {

    interface Interface {
        void doSomethine();
    }

    static class InterfaceImpl implements Interface {
        @Override
        public void doSomethine() {
            System.out.println("InterfaceImpl.doSomething");
        }
    }

    static class InterfaceInvocationHandler implements InvocationHandler {

        private Object object;
        public InterfaceInvocationHandler(Object o){
            this.object = o;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            /**
             * proxy:	需要被代理的对象;
             * method:	对象的方法;
             * args:	方法爱的参数列表;
             */
            return method.invoke(this.object, args);
        }
    }

    public static void main(String[] args) {
        Interface inter = new InterfaceImpl();

        Interface anInterface = (Interface) Proxy.newProxyInstance(
                inter.getClass().getClassLoader(),
                inter.getClass().getInterfaces(),
                new InterfaceInvocationHandler(inter)
        );
        anInterface.doSomethine();
    }


}
