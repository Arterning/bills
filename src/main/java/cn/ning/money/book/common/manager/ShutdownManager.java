package cn.ning.money.book.common.manager;

import cn.ning.money.book.utils.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;


@Slf4j
@Component
public class ShutdownManager {


    @Qualifier(value = "scheduledExecutorService")
    private ScheduledExecutorService scheduledExecutorService;

    @Qualifier(value = "threadPoolExecutor")
    private ThreadPoolExecutor poolExecutor;

    /**
     * 实例在容器销毁前调用
     */
    @PreDestroy
    public void destroy()
    {
        shutdownAsyncManager();
    }

    /**
     * 停止异步执行任务
     */
    private void shutdownAsyncManager()
    {
        try
        {
            log.info("====关闭后台任务任务线程池====");
            ThreadUtil.shutdownAndAwaitTermination(scheduledExecutorService);
            ThreadUtil.shutdownAndAwaitTermination(poolExecutor);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
    }
}
