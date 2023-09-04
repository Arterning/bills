package cn.ning.money.book.service.impl;

import cn.ning.money.book.dto.SpendCategoryDTO;
import cn.ning.money.book.entity.SpendCategoryEntity;
import cn.ning.money.book.mapper.SpendCategoryMapper;
import cn.ning.money.book.service.SpendCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jackbin
 * @since 2020-07-21
 */
@Service
public class SpendCategoryServiceImpl extends ServiceImpl<SpendCategoryMapper, SpendCategoryEntity> implements SpendCategoryService {
    @Autowired
    private SpendCategoryMapper spendCategoryMapper;

    @Override
    public List<SpendCategoryDTO> findAll() {
        return spendCategoryMapper.queryAll();
    }

    @Override
    public List<SpendCategoryEntity> getByRecordTypeId(int recordTypeId) {
        QueryWrapper<SpendCategoryEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("record_type_id",recordTypeId);
        return spendCategoryMapper.selectList(queryWrapper);
    }
}
