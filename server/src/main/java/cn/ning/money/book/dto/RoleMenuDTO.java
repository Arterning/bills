package cn.ning.money.book.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;


@Data
@NoArgsConstructor
public class RoleMenuDTO {

    /**
     * 角色Id
     */
    private Long id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色信息
     */
    private String info;

    private Menu menu;
}
