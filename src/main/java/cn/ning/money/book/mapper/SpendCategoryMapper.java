package cn.ning.money.book.mapper;

import cn.ning.money.book.dto.SpendCategoryDTO;
import cn.ning.money.book.entity.SpendCategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SpendCategoryMapper extends BaseMapper<SpendCategoryEntity> {
    List<SpendCategoryDTO> queryAll();
}
