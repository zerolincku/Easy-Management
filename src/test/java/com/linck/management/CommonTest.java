package com.linck.management;


import org.junit.Test;
import org.springframework.beans.BeanUtils;

/**
 * @program: MyManagement
 * @description
 * @author: Linck
 * @create: 2020-08-11 09:06
 **/
public class CommonTest {
    @Test
    public void beanUtilTest1(){
        A a = new A();
        a.setName("123");
        B b = new B();
        b.setNum(123);
        b.setA(a);
        B b1 = new B();
        BeanUtils.copyProperties(b, b1);
        System.out.println(b.equals(b1));
        System.out.println(b.getNum()==(b1.getNum()));
        System.out.println(b.getA()==(b1.getA()));
        System.out.println(b.getA().getName()==(b1.getA().getName()));
    }

}
class B{
    private Integer num;

    private A a;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }
}
class A{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
