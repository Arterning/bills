package cn.ning.money.book.dto;

import cn.ning.money.book.entity.SpendCategoryEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class SpendCategoryDTO extends SpendCategoryEntity {
    private String recordTypeCode;

    private String recordTypeName;
}
