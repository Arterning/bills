package cn.ning.money.book.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
public class SpendCategoryTotalDTO {
    /**
     * 花费类别Id
     */
    private Long spendCategoryId;

    /**
     * 花费类别名称
     */
    private String spendCategoryName;

    /**
     * 花费总额
     */
    private Double total;
}
