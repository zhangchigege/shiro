package My_java;/**
 * @program: SpringCloud_Parent
 * @description: spring代理实现机制
 * @author: ZhangChi
 * @create: 2019-08-26 18:03
 **/

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @program: SpringCloud_Parent
 *
 * @description: spring代理实现机制,作用是在磁盘上生成一个代理类,并自动完成编译,装载并返回一个实例化对象,
 *
 * @author: ZhangChi
 *
 * @create: 2019-08-26 18:03
 **/
public class ProxyUtil {

    public static Object newProxyInstance(Object target) throws Exception{
        //内容
        String content = "";
        //包信息
        String packageContent = "package My_java;";
        //实例信息,实际类
        Class targetInfo = target.getClass().getInterfaces()[0];
        //名称
        String targetInfoName = targetInfo.getSimpleName();
        //导入的配置
        String importContent = "import " + targetInfo.getName() + ";";
        //类修饰信息
        String classContent = "public class $Proxy implements " + targetInfoName + "{";
        //字段信息
        String fieldContent = "private " + targetInfoName + " target;";
        //构造信息
        String construterContent = "public $Proxy(" + targetInfoName + " target){"
                +"this.target = target;}";
        //方法信息
        String methodsContent = "";
        //获取所有方法
        Method[] methods = targetInfo.getDeclaredMethods();
        for (Method method : methods) {
            //方法名称
            String methodName = method.getName();
            //方法返回类型
            Class returnType = method.getReturnType();
            //方法参数
            Class<?>[] parameterTypes = method.getParameterTypes();
            String argsContent = "";
            String argsNames = "";
            int i = 0;
            for (Class<?> parameterType : parameterTypes) {
                String simpleName = parameterType.getSimpleName();
                argsContent+= simpleName + " p" + i + ",";
                argsNames+="p" + i + ",";
                i++;
            }
            if (argsContent.length() > 0) {
                argsContent = argsContent.substring(0, argsContent.lastIndexOf(",")-1);
                argsNames = argsNames.substring(0, argsNames.lastIndexOf(",")-1);
            }
            methodsContent += "public " + returnType + " " + methodName + "(" + argsContent+"){"
                    +"System.out.println(\"log...\");"
                    +"target." + methodName + "(" + argsNames + ");}";

        }
        //构成整体类信息
        content += packageContent + importContent + classContent + fieldContent + construterContent + methodsContent + "}";
        //创建源文件
        File file = new File("D:\\com\\test\\$Proxy.java");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(content);
        fileWriter.flush();
        fileWriter.close();

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        Iterable units = fileManager.getJavaFileObjects(file);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, units);
        task.call();
        fileManager.close();
        URL[] urls = new URL[]{new URL("file:D:\\")};
        URLClassLoader urlClassLoader = new URLClassLoader(urls);
        Class clazz = urlClassLoader.loadClass("My_java.$Proxy");
        Constructor constructor = clazz.getConstructor(targetInfo);
        Object proxy = constructor.newInstance(target);
        return proxy;
    }

    public static void main(String[] args) throws Exception {
        UserService user = new UserServiceImpl();
        UserService proxy = (UserService) ProxyUtil.newProxyInstance(user);
        proxy.doSomething("张三");
    }

}
