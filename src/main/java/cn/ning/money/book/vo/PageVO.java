package cn.ning.money.book.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;


@ApiModel(value="PageVO对象", description="分页对象")
@Data
public class PageVO {
    /**
     * 分页的索引
     */
    @ApiModelProperty(required = true, value = "分页的索引")
    @PositiveOrZero(message = "当前页数须为整数")
    int pageIndex;

    /**
     * 分页的大小
     */
    @ApiModelProperty(required = true, value = "分页的大小")
    @Positive(message = "分页的大小须为正数")
    int pageSize;
}
