package com.weifuwukt.accesstoken.pojo;

import lombok.Data;

@Data
public class AppEntity {

	private long id;
	private String appId;
	private String appName;
	private String appSecret;
	private String accessToken;
	private String isFlag;
}
