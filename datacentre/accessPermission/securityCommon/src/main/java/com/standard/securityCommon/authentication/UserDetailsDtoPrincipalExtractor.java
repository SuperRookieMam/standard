package com.standard.securityCommon.authentication;

import com.standard.oauthCommon.dto.GrantedAuthorityDto;
import com.standard.oauthCommon.dto.UserDetailsDto;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsDtoPrincipalExtractor implements PrincipalExtractor {

	@Override
	public Object extractPrincipal(Map<String, Object> map) {
		UserDetailsDto userDetailsDto = new UserDetailsDto();
		userDetailsDto.setId(((Integer) map.get("id")).longValue());
		userDetailsDto.setUsername((String) map.get("username"));
		userDetailsDto.setPassword( (String)map.get("password"));
		userDetailsDto.setHeadImage((String)map.get("headImage"));
		userDetailsDto.setLock((boolean)map.get("lock"));
		userDetailsDto.setExpired((boolean)map.get("expired"));
		userDetailsDto.setEnabled((boolean)map.get("enabled"));
		userDetailsDto.setCredentials((String)map.get("credentials"));
		if (!Objects.isNull(map.get("authorities"))) {
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> roles = (List<Map<String, Object>>) map.get("authorities");
			List<GrantedAuthorityDto> authorities = roles.stream()
					.map(role -> new GrantedAuthorityDto())
					.collect(Collectors.toList());
			userDetailsDto.setAuthorities(authorities);
		}
		return userDetailsDto;
	}

}
