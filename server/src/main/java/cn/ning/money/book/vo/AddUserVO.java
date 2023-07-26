package cn.ning.money.book.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class AddUserVO {
    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "昵称不能为空")
    private String nickname;

    @NotNull(message = "性别不能为空")
    private Integer sex;

    private String email;

    @NotNull(message = "密码不能为空")
    private String credential;

    private List<Integer> roles;
}
