package loader;

public class ErrorTester {
    static {
        // 在其它ClassLoader中必需使用一次这个 MyDependence 依赖，
        // 这样在另一个ClassLoader使用的时候就会出错
        System.out.println("ErrorTester MyDependence Location: " +
                loader.ConflictDependence.class.getProtectionDomain().getCodeSource());
    }

    // 不会出错的方法
    public static void test0() {
        System.out.println("ErrorTester MyDependence Location: " +
                ConflictDependence.class.getProtectionDomain().getCodeSource());
    }

    // 出错方法，这个方法的签名依赖的是系统类加载器加载的ConflictDependence
//    public static void test(ConflictDependence a) {
//    }

    // 出错方法，这个方法的签名依赖的是系统类加载器加载的ConflictDependence
    public static ConflictDependence test() {
        return null;
    }
}
