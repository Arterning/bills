package cn.ning.money.book.business;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum WeatherEnum {

    RAIN("rain", "12312", "12"),

    FOG("fog", "12313", "6"),

    WIND("wind", "12314", "3"),

    SUN("sun", "12321", "3");


    private String name;

    private String code;

    private String level;


}
