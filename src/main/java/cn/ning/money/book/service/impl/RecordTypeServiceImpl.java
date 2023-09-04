package cn.ning.money.book.service.impl;

import cn.ning.money.book.entity.RecordTypeEntity;
import cn.ning.money.book.mapper.RecordTypeMapper;
import cn.ning.money.book.service.RecordTypeService;
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
public class RecordTypeServiceImpl extends ServiceImpl<RecordTypeMapper, RecordTypeEntity> implements RecordTypeService {
    @Autowired
    private RecordTypeMapper recordTypeMapper;

    @Override
    public List<RecordTypeEntity> findAll() {
        return recordTypeMapper.selectList(null);
    }

    @Override
    public RecordTypeEntity getByCode(String code) {
        QueryWrapper<RecordTypeEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code", code);
        return recordTypeMapper.selectOne(queryWrapper);
    }
}
