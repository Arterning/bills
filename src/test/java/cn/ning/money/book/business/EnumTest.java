package cn.ning.money.book.business;

import java.util.Collection;
import java.util.EnumSet;

public class EnumTest {

    public static void main(String[] args) {
        Collection<WeatherEnum> list = EnumSet.of(WeatherEnum.SUN, WeatherEnum.FOG);
        WeatherEnum sun = WeatherEnum.SUN;

        System.out.println("test if i need to write equal" + list.contains(sun));
    }
}
