package cn.ning.money.book.common.manager.factory;

import cn.ning.money.book.entity.CommonLogEntity;
import cn.ning.money.book.service.CommonLogService;
import cn.ning.money.book.utils.AddressUtil;
import cn.ning.money.book.utils.SpringContextUtil;

import java.util.TimerTask;

public class AsyncFactory {

    /**
     * 操作日志记录
     * @param logDO 日志信息
     * @return 任务task
     */
    public static TimerTask recordCommonLog(final CommonLogEntity logDO) {
        return new TimerTask() {
            @Override
            public void run() {
                // 远程查询操作地点
                logDO.setOperAddress(AddressUtil.getRealAddressByIP(logDO.getOperIp()));
                SpringContextUtil.getBean(CommonLogService.class).save(logDO);
            }
        };
    }
}
