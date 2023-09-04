package cn.ning.money.book.business;

import cn.ning.money.book.business.OuterClass.StaticInnerClass;

public class OuterClassTest {

    public static void main(String[] args) {
        OuterClass object = new OuterClass();
        OuterClass.InnerClass innerClass = object.new InnerClass();
        StaticInnerClass staticInnerClass = new StaticInnerClass();
    }
}
