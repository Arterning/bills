package cn.ning.money.book.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("tb_record_detail")
public class RecordDetailEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID1 = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 记账人
     */
    private Integer userId;

    /**
     * 分类Id
     */
    private Integer spendCategoryId;

    /**
     * 金额
     */
    private Double amount;

    /**
     * 备注
     */
    private String remark;

    @JsonFormat(pattern ="yyyy-MM-dd",timezone ="GMT+8")
    private Date occurTime;
}
