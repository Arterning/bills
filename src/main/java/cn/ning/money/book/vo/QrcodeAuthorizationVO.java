package cn.ning.money.book.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class QrcodeAuthorizationVO {
    @NotNull(message = "uuid不能为空")
    private String uuid;

    @NotNull(message = "token不能为空")
    private String token;
}
