package cn.ning.money.book.mapper;

import cn.ning.money.book.entity.RoleMenuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jackbin
 * @since 2020-07-21
 */
@Repository
public interface RoleMenuMapper extends BaseMapper<RoleMenuEntity> {

    int deleteBatch(List<Long> ids);
}
