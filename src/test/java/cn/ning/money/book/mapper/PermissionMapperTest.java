package cn.ning.money.book.mapper;

import cn.ning.money.book.entity.MenuEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.util.List;

@SpringBootTest
public class PermissionMapperTest {
    @Autowired
    private MenuMapper mapper;

    @Test
    public void queryPermissionByUserId() {
        List<MenuEntity> menuEntities = mapper.queryUserMenus(1L);
        System.out.println(menuEntities);
    }

}
