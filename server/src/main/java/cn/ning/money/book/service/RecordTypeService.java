package cn.ning.money.book.service;

import cn.ning.money.book.entity.RecordTypeEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jackbin
 * @since 2020-07-21
 */
public interface RecordTypeService extends IService<RecordTypeEntity> {

    List<RecordTypeEntity> findAll();

    RecordTypeEntity getByCode(String code);
}
