package mytest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author HanLiangYuan
 * @version 1.0
 * @since 2019/11/10 12:18
 */
public class TestNullWithJava8Option {
    public static void main(String[] args) {

        List<Country> list = null;

        list = ObjectUtils.defaultIfNull(list, new ArrayList<>());
        boolean quantity = Arrays.asList("xxx").equals(list.stream().map(c -> c.getName()).collect(Collectors.toList()));
        System.out.println("quantity = " + quantity);

        //集合相减
        Collection xxx = CollectionUtils.subtract(list, Arrays.asList("xxx"));
        System.out.println("xxx = " + xxx);

        list.add(new Country("xxx"));
        quantity = Arrays.asList("xxx").equals(list.stream().map(c -> c.getName()).collect(Collectors.toList()));
        System.out.println("quantity = " + quantity);

        quantity = CollectionUtils.isEqualCollection(Arrays.asList("xxx"), list.stream().map(c -> c.getName()).collect(Collectors.toList()));
        System.out.println("quantity = " + quantity);

        list.add(new Country("xxx"));
        quantity = Arrays.asList("xxx").equals(list.stream().map(c -> c.getName()).collect(Collectors.toList()));
        System.out.println("quantity = " + quantity);


        // localdate => date
        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println("zoneId = " + zoneId); // Asia/Shanghai
        LocalDate localDate = LocalDate.now().minusDays(1L);
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        Date d = Date.from(zdt.toInstant());
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d)); // 2019-11-09 00:00:00

        //date => localdate
        //localDate = d.toInstant().atZone(zoneId).toLocalDate();
        //System.out.println("localDate = " + localDate);

        d = DateUtils.addDays(new Date(), -1);  //昨天的这个时候
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d)); //2019-11-09 14:52:44
        // 去掉时分秒
        d = DateUtils.truncate(d, Calendar.DATE);// 截取时间到日
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d)); //2019-11-09 00:00:00

        Calendar cal = Calendar.getInstance();
        //subtracting a day
        cal.add(Calendar.DATE, -1);
        d = new Date(cal.getTimeInMillis());
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d)); //2019-11-09 14:54:45

        // 使用断言: 不满足会抛异常：  java.lang.IllegalArgumentException
        // apache library(commons-lang-2.4.jar)
        //Validate.notNull(country,"country 不应该为空！！");
        //ibrary(spring-2.4.6.jar)
        Assert.notNull(new Country("xxx"), "country 不应该为空！！");

        // 使用java8的option，代码是简洁了，但是会丢失信息，到底是哪个属性的值为null不知道。 ???
        Country country = null;
        String studentName = Optional.ofNullable(country)
                .map(Country::getCity)
                .map(City::getSchool)
                .map(School::getStudent)
                .map(Student::getName)
                .orElse("threre is null");
        System.out.println("studentName = " + studentName);

        country = new Country();
        country.setCity(null);
        studentName = Optional.ofNullable(country)
                .map(Country::getCity)
                .map(City::getSchool)
                .map(School::getStudent)
                .map(Student::getName)
                .orElse("threre is null");
        System.out.println("studentName = " + studentName);

        Country finalCountry = country;
        country.setCity(new City());
        Optional<String> resolve = resolve(() -> finalCountry.getCity().getSchool().getStudent().getName());
        System.out.println("resolve.orElse(\"there is null\") = " + resolve.orElse("there is null"));
    }

    public static <T> Optional<T> resolve(Supplier<T> resolver) {
        try {
            T result = resolver.get();
            return Optional.ofNullable(result);
        } catch (NullPointerException var2) {
            //System.out.println("var2.getMessage() = " + var2.getMessage());
            //System.out.println("var2.getCause() = " + var2.getCause());
            //System.out.println("var2.toString() = " + var2.toString());
            //System.out.println("var2.getLocalizedMessage() = " + var2.getLocalizedMessage());
            //System.out.println("var2.getStackTrace() = " + var2.getStackTrace());
            //var2.printStackTrace();
            return Optional.empty();
        }
    }

}

class Country {
    private City city;
    String name;

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class City {
    private School school;
    String name;

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class School {
    private Student student;
    String name;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Student {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
