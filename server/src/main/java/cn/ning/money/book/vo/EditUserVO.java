package cn.ning.money.book.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;


@ApiModel(value="EditUserVO对象", description="编辑用户对象")
@Data
@NoArgsConstructor
public class EditUserVO {
    @Positive(message = "用户Id为正数")
    private Integer id;

    @NotNull(message = "昵称不能为空")
    private String nickname;

    @NotNull(message = "性别不能为空")
    private Integer sex;

    @NotNull(message = "邮箱不能为空")
    private String email;

    private List<Integer> roles;
}
