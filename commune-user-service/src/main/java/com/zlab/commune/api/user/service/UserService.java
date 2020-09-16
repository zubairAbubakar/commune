package com.zlab.commune.api.user.service;

import com.zlab.commune.api.user.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDto createUser(UserDto userDetails);
    UserDto getUserDetailsByEmail(String email);

    UserDto getUserByUserId(String userId);
}
