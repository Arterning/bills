package cn.ning.money.book.dto;

import cn.ning.money.book.entity.RecordDetailEntity;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class RecordDetailDTO extends RecordDetailEntity {

    /**
     * 花费类别名
     */
    private String spendCategoryName;

    /**
     * 花费类别Code
     */
    private String spendCategoryCode;

}
