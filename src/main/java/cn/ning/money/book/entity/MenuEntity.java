package cn.ning.money.book.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("tb_menu")
public class MenuEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String menuTitle;

    private String menuName;

    private Integer parentId;

    private Integer orderNo;

    private String path;

    private String component;

    /**
     * 是否为外链（0是 1否）
     */
    private Integer outerChain;

    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    private String menuType;

    private String permissionSign;

    private String iconName;
}
