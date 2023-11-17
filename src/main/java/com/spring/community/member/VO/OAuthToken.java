package com.spring.community.member.VO;

import com.spring.community.member.VO.KakaoInfo.KakaoAccount;
import com.spring.community.member.VO.KakaoInfo.Properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OAuthToken {
	private String id_token;
	private String access_token;
	private String token_type;
	private String refresh_token;
	private int expires_in;
	private String scope;
	private int refresh_token_expires_in;
	
}

