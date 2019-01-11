package com.weifuwukt.accesstoken.controller;

import com.alibaba.fastjson.JSONObject;
import com.weifuwukt.accesstoken.base.BaseApiService;
import com.weifuwukt.accesstoken.base.ResponseBase;
import com.weifuwukt.accesstoken.mapper.AccessTokenMapper;
import com.weifuwukt.accesstoken.pojo.AppEntity;
import com.weifuwukt.accesstoken.service.BaseRedisService;
import com.weifuwukt.accesstoken.utils.AccessTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 杨郑兴
 * @Date 2019/1/11 13:09
 * @官网 www.weifuwukt.com
 */
@RestController
public class AccessTokenController  extends BaseApiService {

    @Autowired
    private AccessTokenMapper accessTokenMapper;
    @Autowired
    private AccessTokenUtil accessTokenUtil;
    @Autowired
    private BaseRedisService baseRedisService;
    private static final Long timeToken =(long) (60*60);

    @RequestMapping("/getAccessToken")
    public ResponseBase getAccessToken(AppEntity appEntity){
        //1.获取生成的appid和appSecret
        AppEntity appResult = accessTokenMapper.findApp(appEntity);
        //1.1验证appid和appSecret是否可用
        if(StringUtils.isEmpty(appResult)){
            return setResultError("没有对应机构信息");
        }
        String isFlag = appResult.getIsFlag();
        if(isFlag.equals("1")){
            return setResultError("暂时对应机构不开放，如有疑问请联系我们客户");
        }
        //2.使用appid+appSecret生成生成唯一的AccessToken
        String accessToken = accessTokenUtil.getAccessToken();
        baseRedisService.setString(accessToken,appEntity.getAppId(),timeToken);
        //3.删除之前的AccessToken
        //2、3需要在同一个事务中
        //3.1获取之前的token
        String preAccessToken = appResult.getAccessToken();
        if(!StringUtils.isEmpty(preAccessToken)){
            //第一次AccessToken是为null
            baseRedisService.delKey(preAccessToken);
        }
        //3.2把AccessToken更新到数据库中
        accessTokenMapper.updateAccessToken(accessToken, appEntity.getAppId());
        //4.返回最新的AccessToken
        JSONObject data = new JSONObject();
        data.put("accessToken",accessToken);
        return setResultSuccessData(data);
    }
}
