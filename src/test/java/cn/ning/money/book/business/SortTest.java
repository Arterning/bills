package cn.ning.money.book.business;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class SortTest {

    public static void main(String[] args) {

        Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).stream().sorted().forEach(System.out::println);

        Date date = new Date();
        Date expire = new Date(date.getTime() + 60 * 60 * 1000);
        ArrayList<WeatherDTO> arrayList = Lists.newArrayList(WeatherDTO.builder().weather("rain").date(expire).build(),
                WeatherDTO.builder().weather("sunny").date(date).build());
        arrayList.sort(Comparator.comparing(WeatherDTO::getDate));
        arrayList.sort(new WeatherComparator());

        Collections.sort(arrayList);
        Collections.sort(arrayList, new WeatherComparator());

        System.out.println(JSON.toJSON(arrayList));

    }

}


@Builder
@Getter
@Setter
class WeatherDTO implements Comparable<WeatherDTO> {
    private String city;
    private String weather;
    private Date date;

    public int compareTo(WeatherDTO o) {
        int cityCompare = city.compareTo(o.city);
        if (cityCompare != 0) {
            return cityCompare;
        }
        int weatherCompare = weather.compareTo(o.weather);
        if (weatherCompare != 0) {
            return weatherCompare;
        }
        return date.compareTo(o.date);
    }

}

class WeatherComparator implements Comparator<WeatherDTO> {

    @Override
    public int compare(WeatherDTO o1, WeatherDTO o2) {
        return o1.getDate().compareTo(o2.getDate());
    }

}