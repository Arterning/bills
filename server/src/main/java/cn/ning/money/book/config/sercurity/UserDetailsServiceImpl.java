package cn.ning.money.book.config.sercurity;

import cn.ning.money.book.entity.MenuEntity;
import cn.ning.money.book.entity.UserEntity;
import cn.ning.money.book.service.MenuService;
import cn.ning.money.book.service.UserService;
import cn.ning.money.book.bo.UserMenuBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userDO = userService.getByName(s);
        List<MenuEntity> menuDOList = menuService.getUserMenus(userDO.getId());
        UserMenuBO userPermissionBO = new UserMenuBO(userDO, menuDOList);
        return new JWTUser(userPermissionBO);
    }
}
