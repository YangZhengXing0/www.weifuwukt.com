package com.weifuwukt.accesstoken.interception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weifuwukt.accesstoken.base.BaseApiService;
import com.weifuwukt.accesstoken.mapper.AccessTokenMapper;
import com.weifuwukt.accesstoken.pojo.AppEntity;
import com.weifuwukt.accesstoken.service.BaseRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

//验证AccessToken 是否正确
@Component
public class AccessTokenInterceptor extends BaseApiService implements HandlerInterceptor {
	@Autowired
	private BaseRedisService baseRedisService;
	@Autowired
	private AccessTokenMapper accessTokenMapper;

	//进入controller层之前拦截请求
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o)
			throws Exception {
		String accessToken = request.getParameter("accessToken");
		//1.获取对应的accessToken
		if (org.springframework.util.StringUtils.isEmpty(accessToken)) {
			resultError("accessToken is null",response);
			return false;
		}
		//2.使用accessToken查询redis中对应的value,如果没有获取到的appid,则直接返回错误
		String appid = (String) baseRedisService.getString(accessToken);
		if (org.springframework.util.StringUtils.isEmpty(appid)) {
			// 无效accesstoken
			resultError("this is accesstoken Invlid",response);
			return false;
		}
		//3.查询对应的app信息
		AppEntity app = accessTokenMapper.findAppId(appid);
		if (app == null) {
			resultError("not found app info",response);
			return false;
		}
		//4.获取到is_flag值
		String isFlag = app.getIsFlag();
		if (isFlag.equals("1")) {
			//权限不足
			resultError("no permission",response);
			return false;
		}
		return true;
	}

	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {
		System.out.println("--------------处理请求完成后视图渲染之前的处理操作---------------");
	}

	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception e) throws Exception {
		System.out.println("---------------视图渲染之后的操作-------------------------0");
	}

	// 返回错误提示
	public void resultError(String errorMsg, HttpServletResponse httpServletResponse) throws IOException {
		PrintWriter printWriter = httpServletResponse.getWriter();
		printWriter.write(new JSONObject().toJSONString(setResultError(errorMsg)));
	}

}