package cn.ning.money.book.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class PageBO<T> {
    List<T> list;

    int total;
}
