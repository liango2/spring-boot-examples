package loader;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class Test {
    //链接: 罕见类加载冲突问题：LinkageError - 王鹏亮 的专栏 - CSDN博客
    //https://blog.csdn.net/conquer0715/article/details/51569381
    public static void main(String[] args) throws Exception {
        //模拟说明：
        //1.将以上的4个类打jar包，名为为jse.jar或其他名字
        //2.从jar中删除ErrorTester和Test两个class文件
        //3.修改测试程序Test中的jar路径，运行测试即可看到出错信息
        //
        //问题分析：
        //测试中使用了两个类加载器来加载类，
        //   1. 使用系统类加载器加载ErrorTester，这个类依赖了ConflictDependence
        // !!! 这里调用了ErrorTester.test();
        ErrorTester.test();

        //  2.然后使用自定义的类加载器（一个子优先的类加载器）加载了Sub Class，并通过反射构造其实例，
        // 构造过程中依赖了ConflictDependence，所以会去尝试加载ConflictDependence，
        // 由于自定义的加载器是子优先模式的，会先从自身类加载路径查找并加载，所以可以加载到一个ConflictDependence，
        // 注意这个ConflictDependence和系统类加载器加载的并不是同一个
        URL[] urls = new URL[]{new File("/home/conquer/Desktop/jse.jar").toURI().toURL()};
        SubFirstLoader newLoader = new SubFirstLoader(urls);
        Class<?> b = newLoader.loadClass("loader.SubClass");
        // !!! SubClass的构造函数里面 也 调用了  ErrorTester.test();
        b.newInstance();
    }

    /**
     *  自定义的加载器: 反着来，尝试从自身类路径开始加载
     */
    static class SubFirstLoader extends URLClassLoader {
        public SubFirstLoader(URL[] urls) {
            super(urls);
        }

        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            try {
                // 从自身类路径中加载
                return this.findClass(name);
            } catch (Exception e) {
                // 从父ClassLoader加载
                return super.loadClass(name);
            }
        }
    }
}
