package cn.ning.money.book.controller.record;

import cn.ning.money.book.bo.PageBO;
import cn.ning.money.book.common.anotations.LoginRequired;
import cn.ning.money.book.dto.RecordDTO;
import cn.ning.money.book.constant.CodeMsg;
import cn.ning.money.book.exception.BusinessException;
import cn.ning.money.book.vo.RecordVO;
import cn.ning.money.book.vo.Result;
import cn.ning.money.book.common.LocalUser;
import cn.ning.money.book.constant.RecordConstant;
import cn.ning.money.book.entity.RecordDetailEntity;
import cn.ning.money.book.entity.UserEntity;
import cn.ning.money.book.service.RecordDetailService;
import cn.ning.money.book.dto.RecordDetailDTO;
import cn.ning.money.book.vo.GetRecordsByMonthVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;


@Api(value = "RecordController", tags = { "记账访问接口" })
@RestController
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private RecordDetailService recordDetailService;

    /**
     * 记账
     */
    @LoginRequired
    @ApiOperation(value = "新增记账记录")
    @PostMapping
    public Result<?> createRecord(@RequestBody @Validated RecordVO vo) {
        RecordDTO dto = new RecordDTO();
        BeanUtils.copyProperties(vo, dto);
        UserEntity userDO = LocalUser.get();
        if (recordDetailService.createRecord(dto, userDO.getId())) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.INSERT_RECORD_ERROR);
        }
    }

    @LoginRequired
    @ApiOperation(value = "修改当前登录用户的记账记录")
    @PutMapping("/{id}")
    public Result<?> updateRecord(@PathVariable("id") @Validated @Positive(message = "{id}") Long id, @RequestBody @Validated RecordVO vo) {
        RecordDetailEntity recordDO = recordDetailService.getById(id);
        // 校验
        checkRecord(recordDO);
        RecordDTO dto = new RecordDTO();
        BeanUtils.copyProperties(vo, dto);
        boolean uFlag = recordDetailService.updateRecord(recordDO, dto);
        if (uFlag) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.UPDATE_RECORD_ERROR);
        }
    }

    @LoginRequired
    @ApiOperation(value = "删除当前登录用户的记账记录")
    @DeleteMapping("/{id}")
    public Result<?> deleteBook(@PathVariable("id") @Positive(message = "{id}") Long id) {
        RecordDetailEntity recordDO = recordDetailService.getById(id);
        checkRecord(recordDO);
        boolean delFlag = recordDetailService.deleteById(recordDO.getId());
        if (delFlag) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DEL_RECORD_ERROR);
        }
    }


    @LoginRequired
    @ApiOperation(value = "分页获取某个月份记账记录")
    @PostMapping("/listByMonth")
    public Result<?> getListByMonth(@RequestBody  @Validated GetRecordsByMonthVO vo) {
        if (!vo.getRecordTypeCode().equals(RecordConstant.EXPEND_RECORD_TYPE) && !vo.getRecordTypeCode().equals(RecordConstant.INCOME_RECORD_TYPE)) {
            return Result.error(CodeMsg.RECORD_TYPE_CODE_ERROR);
        }
        UserEntity userDO = LocalUser.get();
        PageBO<RecordDetailDTO> list = recordDetailService.getListByMonth(userDO.getId(), vo.getRecordTypeCode(), vo.getDate(), vo.getPageIndex(), vo.getPageSize());
        return Result.success(list);
    }

    private void checkRecord(RecordDetailEntity recordDetailDO) {
        if (recordDetailDO == null) {
            throw new BusinessException(CodeMsg.NOT_FIND_DATA);
        }
        // 校验是否为当前登录人的记账记录
        UserEntity userDO = LocalUser.get();
        if (!recordDetailDO.getUserId().equals(userDO.getId().intValue())) {
            throw new BusinessException(CodeMsg.DEL_RECORD_FORBIDDEN);
        }
    }
}
