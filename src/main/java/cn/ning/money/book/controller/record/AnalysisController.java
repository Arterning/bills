package cn.ning.money.book.controller.record;

import cn.ning.money.book.bo.MonthRecordBO;
import cn.ning.money.book.common.LocalUser;
import cn.ning.money.book.common.anotations.CommonLog;
import cn.ning.money.book.common.anotations.LoginRequired;
import cn.ning.money.book.common.enums.BusinessType;
import cn.ning.money.book.constant.CodeMsg;
import cn.ning.money.book.constant.RecordConstant;
import cn.ning.money.book.dto.SpendCategoryTotalDTO;
import cn.ning.money.book.entity.UserEntity;
import cn.ning.money.book.service.RecordDetailService;
import cn.ning.money.book.utils.DateUtil;
import cn.ning.money.book.vo.GetSixMonthRecordsVO;
import cn.ning.money.book.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Api(value = "AnalysisController", tags = { "分析访问接口" })
@RestController
@RequestMapping("/analysis")
public class AnalysisController {
    @Autowired
    private RecordDetailService recordDetailService;

    @LoginRequired
    @CommonLog(title = "获取某年所有消费类别的总额", businessType = BusinessType.QUERY)
    @ApiOperation(value = "获取某年所有消费类别的总额")
    @PreAuthorize("hasAuthority('record:analysis:spendCategoryTotal')")
    @GetMapping("/spendCategoryTotal/{year}/{recordType}")
    public Result<?> getSpendCategoryTotalInYear(@ApiParam(required = true, value = "年（yyyy）") @Validated
                                                 @DateTimeFormat(pattern="yyyy") @PathVariable(value = "year") Date date,
                                                 @NotNull(message = "记账类型编码不能为空") @PathVariable(value = "recordType")String recordType) {
        if (!recordType.equals(RecordConstant.EXPEND_RECORD_TYPE) && !recordType.equals(RecordConstant.INCOME_RECORD_TYPE)) {
            return Result.error(CodeMsg.RECORD_TYPE_CODE_ERROR);
        }
        UserEntity userDO = LocalUser.get();
        List<SpendCategoryTotalDTO> list = recordDetailService.getSpendSpendCategoryTotalByYear(userDO.getId(), recordType,
                date);
        return Result.success(list);
    }

    @LoginRequired
    @CommonLog(title = "获取最近六个月的支出和收入", businessType = BusinessType.QUERY)
    @ApiOperation(value = "获取最近六个月的支出和收入")
    @PreAuthorize("hasAuthority('record:analysis:latestSixMonthList')")
    @PostMapping("/latestSixMonthList")
    public Result<?> getLatestSixMonthList(@RequestBody @Validated GetSixMonthRecordsVO vo) {
        if (!vo.getRecordTypeCode().equals(RecordConstant.EXPEND_RECORD_TYPE) && !vo.getRecordTypeCode().equals(RecordConstant.INCOME_RECORD_TYPE)) {
            return Result.error(CodeMsg.RECORD_TYPE_CODE_ERROR);
        }
        UserEntity userDO = LocalUser.get();
        List<MonthRecordBO> list = recordDetailService.getLatestSixMonthList(userDO.getId(), vo.getRecordTypeCode(),
                vo.getBeginDate(), DateUtil.addMonth(vo.getBeginDate(), 6));
        return Result.success(list);
    }
}
