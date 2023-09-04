package cn.ning.money.book.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AccountBaseSettingVO {
    @NotNull(message = "昵称不能为空")
    private String nickname;

    @NotNull(message = "性别不能为空")
    private Integer sex;
}
