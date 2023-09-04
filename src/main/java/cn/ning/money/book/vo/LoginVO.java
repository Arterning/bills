package cn.ning.money.book.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@ApiModel(value="LoginDTO对象", description="用户登录对象")
@Data
public class LoginVO {

    @ApiModelProperty(required = true, value = "用户名")
    private String username;

    @ApiModelProperty(required = true, value = "密码")
    private String password;
}
