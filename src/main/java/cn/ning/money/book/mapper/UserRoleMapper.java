package cn.ning.money.book.mapper;

import cn.ning.money.book.entity.UserRoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jackbin
 * @since 2020-07-21
 */
@Repository
public interface UserRoleMapper extends BaseMapper<UserRoleEntity> {
    int deleteByUserId(int userId);
}
