package mytest;

/**
 * @author HanLiangYuan
 * @version 1.0
 * @since 2019/11/24 17:10
 */
public class TestTryCatchThrowCatch {

    public static void main(String[] args) {
        try {
            throw new java.io.IOException();
        } catch (java.io.IOException exc) {
            System.err.println("In catch IOException: " + exc.getClass() +", e.msg="+ exc.getMessage());
            throw new RuntimeException();
        } catch (Exception exc) {
            System.err.println("In catch Exception: " + exc.getClass()+", e.msg="+ exc.getMessage());
        } finally {
            System.err.println("In finally");
        }
    }
}
