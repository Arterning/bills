package cn.ning.money.book.business;

import com.alibaba.fastjson.JSON;

import java.util.*;

public class MapDataTest {

    private static final Map<String, List<String>> phaseMap = Map.of(
            "幼苗期", Arrays.asList("幼苗期", "再生苗期"),
            "分蘖期", Arrays.asList("分蘖期", "返青期"),
            "穗期", Arrays.asList("幼穗分化期", "抽穗扬花期"),
            "灌浆期", Arrays.asList("灌浆期", "再生抽穗灌浆期", "再生成熟收获期", "完熟期"),
            "xx", List.of("lkk"),
            "yytr", List.of("lkk"),
            "we", List.of("lkk"),
            "xwex", List.of("lkk"));

    public static void main(String[] args) {
        System.out.println(JSON.toJSON(phaseMap));
    }

}
