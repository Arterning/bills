package cn.ning.money.book.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
public class MonthRecordBO {
    // 月份
    @JsonFormat(pattern="yyyy-MM",timezone = "GMT+8")
    private Date occurMonth;

    // 总额
    private Double total = 0.0;
}
