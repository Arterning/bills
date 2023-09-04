package cn.ning.money.book.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


@ApiModel(value="GetMenusVO对象", description="分页获取菜单列表对象")
@Data
public class GetMenusVO {
    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "状态")
    private Boolean deleted;

    @ApiModelProperty(required = true, value = "年月日（yyyy-MM-dd）")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date date;
}
