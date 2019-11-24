package mytest;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Objects;
import java.util.Optional;

/**
 * @author HanLiangYuan
 * @version 1.0
 * @since 2019/11/19 0:28
 */
public class ParseObjectToLong {

    public static void main(String[] args) {
        System.out.println("ConvertUtils.convert(\"123\", Long.class) = " + ConvertUtils.convert("123", Long.class));
        System.out.println("ConvertUtils.convert(123, Long.class) = " + ConvertUtils.convert(123, Long.class));

//Object o = 123;
Object o = "123";
//Object o = null;
        Object o1 = Optional.ofNullable(o).map(i -> ConvertUtils.convert(i, Long.class)).orElse(null);
        System.out.println("o1 = " + o1);
    }
}
