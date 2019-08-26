package My_java;/**
 * @program: SpringCloud_Parent
 * @description: java静态代理
 * @author: ZhangChi
 * @create: 2019-08-26 16:13
 **/

/**
 * @program: SpringCloud_Parent
 *
 * @description: java静态代理
 *
 * @author: ZhangChi
 *
 * @create: 2019-08-26 16:13
 **/
public class JavaStaticProxyDemo {

    /**
     * 业务接口
     */
    interface Interface{
        void doSomething();
    }

    /**
     * 具体业务实现类,真实类
     */
    static class InterfaceImpl implements Interface{

        @Override
        public void doSomething() {
            System.out.println("InterfaceImpl.doSomething()");
        }
    }

    /**
     * 代理类,通过代理接口,操作具体真实类
     */
    static class  InterfaceProxy implements Interface {
        private Interface anInterface;

        public InterfaceProxy(Interface anInterface){
            this.anInterface = anInterface;
        }

        @Override
        public void doSomething() {
            System.out.println("before-work of doSomething");
            this.anInterface.doSomething();
            System.out.println("after-work of doSomething");
        }
    }

    public static void main(String[] args) {
        Interface real = new InterfaceImpl();
        InterfaceProxy proxy = new InterfaceProxy(real);

        proxy.doSomething();
    }






}
