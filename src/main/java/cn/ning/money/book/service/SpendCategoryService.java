package cn.ning.money.book.service;

import cn.ning.money.book.dto.SpendCategoryDTO;
import cn.ning.money.book.entity.SpendCategoryEntity;
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
public interface SpendCategoryService extends IService<SpendCategoryEntity> {
    /**
     * 列出所有花费分类
     * @return
     */
    List<SpendCategoryDTO> findAll();

    /**
     * 通过记账类别Id获取花费分类
     * @param recordTypeId
     * @return
     */
    List<SpendCategoryEntity> getByRecordTypeId(int recordTypeId);
}
