package cn.ning.money.book.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@Builder
public class MenuBO {

    private Integer id;

    /**
     * 父节点Id
     */
    private Integer parentId;

    /**
     * 菜单标题
     */
    private String menuTitle;

    /**
     * 菜单名
     */
    private String menuName;

    /**
     * 路径
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 是否为外链
     */
    private boolean isOuterChain;

    private String menuType;

    private String iconName;

    private String permissionSign;

    private Integer orderNo;

    /**
     * 是否拥有
     */
    private boolean owned;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date deleteTime;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<MenuBO> children;

    public MenuBO() {
    }
}
