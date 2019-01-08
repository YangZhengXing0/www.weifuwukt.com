package ratelmiter.controller;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ratelmiter.annatation.MyRateLimter;
import ratelmiter.service.OrderService;

import java.util.concurrent.TimeUnit;

/**
 * rateLimiter限流，限流最好放在网关那一层
 * @author 杨郑兴
 * @Date 2019/1/8 19:42
 * @官网 www.weifuwukt.com
 */
@RestController
public class IndexController {

    @Autowired
    private OrderService orderService;
    //表示 每秒中生成1个令牌存放在桶中，并创建独立线程
    private RateLimiter rateLimiter = RateLimiter.create(1.0);//独立线程

    //方式一
    @RequestMapping("/order")
    public String order(){
        //1.限流判断
        //尝试获取令牌:如果在500毫秒内，没有获取到令牌的话，则直接走服务降级
        boolean acquire = rateLimiter.tryAcquire(500, TimeUnit.MILLISECONDS);
        //rateLimiter.acquire()该方法会阻塞线程，直到令牌桶中能取到令牌为止才继续向下执行，并返回等待的时间
        System.out.println("是否拿到令牌："+acquire+"从桶中获取令牌等待的时间："+rateLimiter.acquire());
        System.out.println("是否拿到令牌："+acquire);
        //true表示在500毫秒内拿到了令牌，flase表示在500毫秒内没有拿到令牌
        if(!acquire){
            System.out.println("你在怎么抢，也抢不到，因为会一直等待的，你先放弃吧！");
            return "你在怎么抢，也抢不到，因为会一直等待的，你先放弃吧！";
        }
        //执行业务
        boolean isOrderAdd = orderService.addOrder();
        if (isOrderAdd) {
            return "恭喜您,抢购成功!";
        }
        return "抢购失败!";
    }

    //方式二：自定义注解方式
    @RequestMapping("/findAll")
    @MyRateLimter(CreateRate=1,timeout = 600)
    public String findAll(){
        System.out.println("findAll");
        //执行业务
        boolean isOrderAdd = orderService.addOrder();
        if (isOrderAdd) {
            return "成功!";
        }
        return "失败!";
    }
}
