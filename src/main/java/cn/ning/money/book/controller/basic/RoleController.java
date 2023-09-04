package cn.ning.money.book.controller.basic;


import cn.ning.money.book.bo.MenuBO;
import cn.ning.money.book.constant.CodeMsg;
import cn.ning.money.book.constant.RoleConstant;
import cn.ning.money.book.entity.MenuEntity;
import cn.ning.money.book.entity.RoleEntity;
import cn.ning.money.book.exception.BusinessException;
import cn.ning.money.book.service.MenuService;
import cn.ning.money.book.service.RoleService;
import cn.ning.money.book.vo.AddRoleVO;
import cn.ning.money.book.vo.EditRoleVO;
import cn.ning.money.book.vo.GetRolesVO;
import cn.ning.money.book.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jackbin
 * @since 2020-07-21
 */
@Api(value = "RoleController", tags = { "角色相关接口" })
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "获取角色列表")
    @PreAuthorize("hasAuthority('system:role:view')")
    @PostMapping("/page")
    public Result<?> getRoleList(@RequestBody @Validated GetRolesVO vo) {
        return Result.success(roleService.getByPage(vo.getName(), vo.getDeleted(), vo.getDate(), vo.getPageNo() - 1, vo.getPageSize()));
    }

    @ApiModelProperty(value = "添加菜单")
    @PreAuthorize("hasAuthority('system:role:add')")
    @PostMapping("/add")
    public Result<?> addRole(@RequestBody @Validated AddRoleVO vo) {
        if (roleService.add(vo.getName(), vo.getInfo())) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.ADD_DATA_ERROR);
        }
    }

    @ApiOperation(value = "角色所拥有的权限")
    @PreAuthorize("hasAuthority('system:role:ownedMenus')")
    @GetMapping(value = "/ownedMenus/{roleId}")
    public Result<?> getOwnedMenus(@ApiParam("角色Id") @Validated
                                   @Positive(message = "角色Id为正数") @PathVariable("roleId") Integer roleId) {
        // 所有权限
        List<MenuEntity> allMenus = menuService.getAll();
        // 角色的权限
        List<MenuEntity> roleMenus = menuService.getRoleMenus((long)roleId);
        List<MenuBO> boList = menuService.copyFromMenuDos(allMenus);
        // 此处使用字典
        Map<Integer,Object> dictionaries = new HashMap<>();
        roleMenus.forEach(n -> dictionaries.put(n.getId().intValue(), null));
        boList.forEach(n -> {
            if (dictionaries.containsKey(n.getId())) {
                n.setOwned(true);
            }
        });
        List<MenuBO> finalMenus = menuService.generatorMenuTree(boList);
        return Result.success(finalMenus);
    }

    @ApiOperation(value = "编辑角色")
    @PreAuthorize("hasAuthority('system:role:edit')")
    @PutMapping(value = "/edit")
    public Result<?> editRole(@RequestBody @Validated EditRoleVO vo) {
        RoleEntity role = roleService.getById(vo.getId());
        // 修改的角色名与原角色名不相等才需要校验
        if (!role.getName().equals(vo.getName())) {
            checkRoleName(role.getName());
        }
        roleService.edit(vo.getId(), vo.getName(), vo.getInfo(), vo.getMenuIds());
        return Result.success();
    }

    @ApiOperation(value = "删除角色")
    @PreAuthorize("hasAuthority('system:role:del')")
    @DeleteMapping(value = "/{id}")
    public Result<?> delRole(@PathVariable @Validated @Positive(message = "角色Id需为正数") Integer id) {
        RoleEntity role = roleService.getById(id);
        checkRoleName(role.getName());
        roleService.removeById(id);
        return Result.success();
    }

    @ApiOperation(value = "还原角色")
    @PreAuthorize("hasAuthority('system:role:reset')")
    @PutMapping(value = "/reset/{id}")
    public Result<?> resetRole(@PathVariable @Validated @Positive(message = "角色Id需为正数") Integer id) {
        roleService.reset(id);
        return Result.success();
    }

    private void checkRoleName(String name) {
        if (RoleConstant.ROOT.equals(name) || RoleConstant.DEFAULT.equals(name)) {
            throw new BusinessException(CodeMsg.ROLE_EDIT_NOT_ALLOWED);
        }
    }
}
