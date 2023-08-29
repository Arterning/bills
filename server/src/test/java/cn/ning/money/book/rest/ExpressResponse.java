package cn.ning.money.book.rest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpressResponse {

    String code;

    String msg;

    Object data;
}
