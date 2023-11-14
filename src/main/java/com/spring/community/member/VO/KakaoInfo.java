package com.spring.community.member.VO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoInfo {
	private String id;
	private String connected_at;
	private String synched_at;
	private Properties properties;
	private KakaoAccount kakao_account;
	
	@Getter
	@Setter
	@JsonIgnoreProperties(ignoreUnknown = true)
	public class Properties {
		private String nickname;
		private String profile_image;
		private String thumbnail_image;
	}
	
	@Getter
	@Setter
	@JsonIgnoreProperties(ignoreUnknown = true)
	public class KakaoAccount {
		private boolean profile_nickname_needs_agreement;
		private boolean profile_image_needs_agreement;
		private boolean name_needs_agreement;
		private Profile profile;
		private String name;
		private boolean has_email;
		private boolean email_needs_agreement;
		private boolean is_email_valid;
		private boolean is_email_verified;
		private String email;
		private boolean has_phone_number;
		private boolean phone_number_needs_agreement;
		private String phone_number;
		private boolean has_age_range;
		private boolean age_range_needs_agreement;
		private String age_range;
		private boolean has_birthyear;
		private boolean birthyear_needs_agreement;
		private int birthyear;
		private boolean has_birthday;
		private boolean birthday_needs_agreement;
		private int birthday;
		private String birthday_type;
		private boolean has_gender;
		private boolean gender_needs_agreement;
		private String gender;
		
		@Getter
		@Setter
		@JsonIgnoreProperties(ignoreUnknown = true)
		public class Profile {
			private String nickname;
			private String thumbnail_image_url;
			private String profile_image_url;
			@JsonIgnore
			private boolean is_default_image;
		}
		
	}
	
}
