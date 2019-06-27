package com.standard.permission.cover;

import com.standard.oauthCommon.dto.UserDetailsDto;
import com.standard.permission.entity.OAthUserDetailes;

public class UserDetailsCover {
    public static UserDetailsDto toDto(OAthUserDetailes mUserDetails){
        UserDetailsDto userDetailsDto =new UserDetailsDto();
        userDetailsDto.setId(mUserDetails.getId());
        userDetailsDto.setUsername(mUserDetails.getUsername());
        userDetailsDto.setPassword(mUserDetails.getPassword());
        userDetailsDto.setHeadImage(mUserDetails.getHeadImage());
        userDetailsDto.setAuthorities(mUserDetails.getAuthorities());
        userDetailsDto.setExpired(mUserDetails.isExpired());
        userDetailsDto.setLock(mUserDetails.isLock());
        userDetailsDto.setCredentials(mUserDetails.getCredentials());
        userDetailsDto.setEnabled(mUserDetails.isEnabled());
        return  userDetailsDto;
    }
}
