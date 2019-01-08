package order.service;

import com.alibaba.fastjson.JSONObject;
import order.utils.HttpClientUtils;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	public JSONObject getMember() {

		JSONObject result = HttpClientUtils.httpGet("http://127.0.0.1:8081/member/memberIndex");
		return result;
	}
}
