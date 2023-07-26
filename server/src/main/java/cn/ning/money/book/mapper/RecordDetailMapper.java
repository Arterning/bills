package cn.ning.money.book.mapper;

import cn.ning.money.book.dto.SpendCategoryTotalDTO;
import cn.ning.money.book.entity.RecordDetailEntity;
import cn.ning.money.book.dto.RecordDetailDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface RecordDetailMapper extends BaseMapper<RecordDetailEntity> {

    /**
     * 根据月份查询总额
     * @param userId 用户Id
     * @param recordTypeCode 记录类型Code
     * @param date 时间
     * @return List
     */
    Double querySpendTotalByMonth(Long userId, String recordTypeCode, Date date);

    /**
     * 获取花费类别的消费总额
     * @param userId 用户Id
     * @param recordTypeCode 记账类型Code
     * @param date 时间
     * @param begin 索引
     * @param end 索引
     * @return
     */
    List<SpendCategoryTotalDTO> querySpendSpendCategoryTotalByMonth(Long userId, String recordTypeCode, Date date, int begin, int end);

    List<SpendCategoryTotalDTO> querySpendSpendCategoryTotalByYear(Long userId, String recordTypeCode, Date date);

    /**
     * 查询用户某个月内的记账记录
     */
    IPage<RecordDetailDTO> queryByMonth(Page<?> page, Long userId, String recordTypeCode, Date date);

    List<RecordDetailDTO> queryByInterval(Long userId, String recordTypeCode, Date beginDate, Date endDate);
}
