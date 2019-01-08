package order.controller;

import com.alibaba.fastjson.JSONObject;
import order.hystrix.OrderHystrixCommand;
import order.hystrix.OrderHystrixCommand2;
import order.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private MemberService memberService;

	@RequestMapping("/orderIndex")
	public Object orderIndex() throws InterruptedException {
		JSONObject member = memberService.getMember();
		System.out.println("当前线程名称:" + Thread.currentThread().getName() + ",订单服务调用会员服务:member:" + member);
		return member;
	}

	// 已经理解
	@RequestMapping("/orderIndexHystrix")
	public Object orderIndexHystrix() throws InterruptedException {
		return new OrderHystrixCommand(memberService).execute();
	}

	@RequestMapping("/orderIndexHystrix2")
	public Object orderIndexHystrix2() throws InterruptedException {
		return new OrderHystrixCommand2(memberService).execute();
	}

	@RequestMapping("/findOrderIndex")
	public Object findIndex() {
		System.out.println("当前线程:" + Thread.currentThread().getName() + ",findOrderIndex");
		return "findOrderIndex";
	}
}
