package cn.ning.money.book.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConstructMapTest {

    public static void main(String[] args) {
        Map<String, String> dict = new HashMap<>() {{
            put("1", "35");
            put("2", "36");
            put("3", "38");
            put("4", "41");
        }};

        System.out.println(dict.get("1"));

        ArrayList<String> arrayList = new ArrayList<>() {{
            add("sdf");
        }};

    }
}
