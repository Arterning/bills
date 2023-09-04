package cn.ning.money.book.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;


@ApiModel(value="GetSixMonthRecordsVO对象", description="获取记账记录对象")
@Data
@NoArgsConstructor
public class GetSixMonthRecordsVO {

    @ApiModelProperty(required = true, value = "年月（yyyy-MM）")
    @JsonFormat(pattern="yyyy-MM",timezone = "GMT+8")
    private Date beginDate;

    @ApiModelProperty(required = true, value = "记录类型编码（支出:expendType;收入:incomeType）")
    @NotNull(message = "记录类型编码不能为空")
    private String recordTypeCode;
}
