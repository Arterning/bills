package cn.ning.money.book.controller.basic;


import cn.ning.money.book.bo.MenuBO;
import cn.ning.money.book.bo.PageBO;
import cn.ning.money.book.common.LocalUser;
import cn.ning.money.book.common.LocalUserId;
import cn.ning.money.book.common.anotations.LoginRequired;
import cn.ning.money.book.constant.CodeMsg;
import cn.ning.money.book.constant.SexConstant;
import cn.ning.money.book.dto.UserRoleDTO;
import cn.ning.money.book.entity.MenuEntity;
import cn.ning.money.book.entity.RoleEntity;
import cn.ning.money.book.service.MenuService;
import cn.ning.money.book.service.RoleService;
import cn.ning.money.book.service.UserRoleService;
import cn.ning.money.book.utils.PasswordUtil;
import cn.ning.money.book.entity.UserEntity;
import cn.ning.money.book.service.UserService;
import cn.ning.money.book.vo.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jackbin
 * @since 2020-07-21
 */
@Api(value = "UserController", tags = { "用户管理接口" })
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private MenuService menuService;

    @LoginRequired
    @ApiOperation(value = "获取用户的角色及菜单")
    @GetMapping("/roleMenus")
    public Result<?> getRoleMenus() {
        // 用户角色
        List<RoleEntity> roleDOS = roleService.getByUserId(LocalUserId.get());
        // 用户菜单权限
        List<MenuEntity> menuDOS = menuService.getUserMenus(LocalUserId.get());
        List<MenuBO> menuBOS =  menuService.copyFromMenuDos(menuDOS);
        List<MenuBO> tree = menuService.generatorMenuTree(menuBOS);
        RoleMenuVO roleMenuVO = new RoleMenuVO(LocalUser.get(), roleDOS, tree);
        return Result.success(roleMenuVO);
    }

    @ApiOperation(value = "分页获取用户列表")
    @PreAuthorize("hasAuthority('system:user:view')" )
    @PostMapping(value = "/page")
    public Result<?> getByPage(@RequestBody @Validated GetUsersVO vo) {
        PageBO<UserEntity> pageBO = userService.getByPage(vo.getUsername(), vo.getDeleted(), vo.getDate(),
                vo.getPageNo() - 1, vo.getPageSize());
        return Result.success(pageBO);
    }

    @ApiOperation(value = "根据用户编号获取详细信息")
    @PreAuthorize("hasAuthority('system:user:detail')" )
    @GetMapping(value = "/{userId}")
    public Result<?> getUserById(@Validated @PositiveOrZero @PathVariable(value = "userId") Integer userId) {
        UserRoleDTO ur = new UserRoleDTO();
        if (userId > 0) {
            UserEntity userDO = userService.getById(userId);
            BeanUtils.copyProperties(userDO, ur);
            List<RoleEntity> list = roleService.getByUserId(Long.valueOf(userId));
            ur.setOwnedRoles(list);
        }
        ur.setAllRoles(roleService.getRoles());
        return Result.success(ur);
    }

    @ApiOperation(value = "编辑用户")
    @PreAuthorize("hasAuthority('system:user:edit')" )
    @PutMapping(value = "/edit")
    public Result<?> editUser(@RequestBody @Validated EditUserVO vo) {
        if (SexConstant.MAN !=vo.getSex() && SexConstant.WOMAN != vo.getSex()) {
            return Result.error(CodeMsg.SEX_FORMAT_ERROR);
        }
        // 编辑用户
        userService.edit(vo.getId(), vo.getNickname(), vo.getSex(), vo.getEmail());
        // 编辑用户角色
        userRoleService.edit(vo.getId(), vo.getRoles());
        return Result.success();
    }

    @ApiOperation(value = "添加用户")
    @PreAuthorize("hasAuthority('system:user:add')" )
    @PostMapping(value = "/add")
    public Result<?> addUser(@RequestBody @Validated AddUserVO vo) {
        if (userService.getByName(vo.getUsername()) != null) {
            return Result.error(CodeMsg.USERNAME_EXIST);
        }
        if (!PasswordUtil.check(vo.getCredential())) {
            return Result.error(CodeMsg.PSW_FORMAT_ERROR);
        }
        if (SexConstant.MAN !=vo.getSex() && SexConstant.WOMAN != vo.getSex()) {
            return Result.error(CodeMsg.SEX_FORMAT_ERROR);
        }
        /// 新增用户
        userService.add(vo.getUsername(), vo.getNickname(), vo.getSex(), vo.getEmail(), vo.getCredential());
        // 编辑用户角色
        UserEntity userDO = userService.getByName(vo.getUsername());
        userRoleService.edit(userDO.getId().intValue(), vo.getRoles());
        return Result.success();
    }

    @ApiOperation(value = "删除用户")
    @PreAuthorize("hasAuthority('system:user:del')" )
    @DeleteMapping("/{id}")
    public Result<?> delUser(@PathVariable @Validated @Positive(message = "用户Id需为正数") Integer id) {
        userService.removeById(id);
        return Result.success();
    }

    @ApiOperation(value = "恢复用户")
    @PutMapping("/reset/{id}")
    @PreAuthorize("hasAuthority('system:user:reset')" )
    public Result<?> resetUser(@PathVariable @Validated @Positive(message = "用户Id需为正数") Integer id) {
        userService.reset(id);
        return Result.success();
    }
}
