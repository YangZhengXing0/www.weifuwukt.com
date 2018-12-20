package async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author 杨郑兴
 * @Date 2018/12/20 17:15
 * @官网 www.weifuwukt.com
 */
@Component
@Slf4j
public class IndexService {

    @Async//这里的返回结果，被调用者  可不能接受到
    public String async(){
        log.info("2");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("3");
        return "这是返回结果";
    }

    //模拟实现异步调用，这里的返回结果，被调用者  可接受到
    public String hello(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("2");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("3");
            }
        });
        return "这是返回结果";
    }
}
