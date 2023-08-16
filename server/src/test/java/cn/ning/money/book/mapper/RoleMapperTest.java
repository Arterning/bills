package cn.ning.money.book.mapper;

import cn.ning.money.book.entity.RoleEntity;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.management.relation.Role;
import java.util.List;

@SpringBootTest
public class RoleMapperTest {


    @Resource
    RoleMapper roleMapper;

    @Test
    public void queryRoleByUserId() {
        List<RoleEntity> roles = roleMapper.selectList(null);
        System.out.println(JSON.toJSON(roles));
    }


}
