package cn.ning.money.book.controller.record;

import cn.ning.money.book.common.LocalUser;
import cn.ning.money.book.common.anotations.LoginRequired;
import cn.ning.money.book.constant.RecordConstant;
import cn.ning.money.book.dto.SpendCategoryTotalDTO;
import cn.ning.money.book.entity.UserEntity;
import cn.ning.money.book.service.RecordDetailService;
import cn.ning.money.book.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@Api(value = "AnalysisController", tags = { "主页访问接口" })
@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private RecordDetailService recordDetailService;

    @LoginRequired
    @ApiOperation(value = "获取某个月份的支出和收入")
    @GetMapping("/spendTotalInMonth/{date}")
    public Result<?> getSpendTotalInMonth(@ApiParam(required = true, value = "年月（yyyy-MM）") @Validated
                                          @DateTimeFormat(pattern="yyyy-MM") @PathVariable(value = "date") Date date) {
        UserEntity userDO = LocalUser.get();
        List<Double> list = recordDetailService.getSpendTotalByMonth(userDO.getId(), date);
        return Result.success(list);
    }

    @LoginRequired
    @ApiOperation(value = "获取某个月份前三消费类别")
    @GetMapping("/topThreeSpendCategoryTotal/{date}")
    public Result<?> getTopThreeSpendTotal(@ApiParam(required = true, value = "年月（yyyy-MM）") @Validated
                                           @DateTimeFormat(pattern="yyyy-MM") @PathVariable(value = "date")Date date) {
        UserEntity userDO = LocalUser.get();
        List<SpendCategoryTotalDTO> list = recordDetailService.getSpendTotalBySpendCategory(userDO.getId(), RecordConstant.EXPEND_RECORD_TYPE,
                date, 0, 3);
        return Result.success(list);
    }
}
