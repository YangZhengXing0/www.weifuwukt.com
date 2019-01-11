package com.weifuwukt.accesstoken.controller;

import com.weifuwukt.accesstoken.base.BaseApiService;
import com.weifuwukt.accesstoken.base.ResponseBase;
import com.weifuwukt.accesstoken.mapper.AccessTokenMapper;
import com.weifuwukt.accesstoken.pojo.AppEntity;
import com.weifuwukt.accesstoken.service.BaseRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 杨郑兴
 * @Date 2019/1/12 0:21
 * @官网 www.weifuwukt.com
 */
@RestController
@RequestMapping("/openApi")
public class MemberController extends BaseApiService {

    @Autowired
    private BaseRedisService baseRedisService;
    @Autowired
    private AccessTokenMapper accessTokenMapper;

    @RequestMapping("/getUser")
    public ResponseBase getUser(String accessToken) {
       /* //1.获取对应的accessToken
        if (StringUtils.isEmpty(accessToken)) {
            return setResultError("accessToken is null");
        }
        //2.使用accessToken查询redis中对应的value,如果没有获取到的appid,则直接返回错误
        String appid = (String) baseRedisService.getString(accessToken);
        if (StringUtils.isEmpty(appid)) {
            // 无效accesstoken
            return setResultError("this is accesstoken Invlid");
        }
        //3.查询对应的app信息
        AppEntity app = accessTokenMapper.findAppId(appid);
        if (app == null) {
            return setResultError("not found app info");
        }
        //4.获取到is_flag值
        String isFlag = app.getIsFlag();
        if (isFlag.equals("1")) {
            //权限不足
            return setResultError("no permission");
        }*/
        //5.直接调用真实业务场景
        System.out.println("调用接口了");
        return setResultSuccess("调用接口了");
    }
}