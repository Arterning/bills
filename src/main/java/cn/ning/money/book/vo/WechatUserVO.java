package cn.ning.money.book.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@ApiModel(value="WechatUserVO对象", description="微信登录对象")
@Data
@NoArgsConstructor
public class WechatUserVO {
    @ApiModelProperty(required = true,value = "微信的用户OpenId")
    @NotBlank(message = "openId不能为空")
    private String openId;

    @ApiModelProperty(required = true, value = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(required = true, value = "用户昵称")
    @NotBlank(message = "用户昵称不能为空")
    private String nickname;

    @ApiModelProperty(required = true, value = "性别（男:1;女:2）")
    @NotNull(message = "性别不能为空")
    private Integer sex;

    @ApiModelProperty(required = true, value = "头像地址")
    private String avatarUrl;
}
