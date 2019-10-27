package loader;

public class SubClass {
    private ConflictDependence myDependence;

    /**
     * 构造函数
     */
    public SubClass() {
        // 构造过程中依赖了ConflictDependence，所以会去尝试加载ConflictDependence
        System.out.println(ConflictDependence.class.getProtectionDomain().getCodeSource());
        //在SubClass中调用了ErrorTester.test()方法，这个方法的签名依赖的是系统类加载器加载的ConflictDependence
        ConflictDependence test = ErrorTester.test();
    }
}
