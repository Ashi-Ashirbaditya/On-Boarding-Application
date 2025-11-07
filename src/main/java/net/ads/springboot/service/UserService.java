package net.ads.springboot.service;

import net.ads.springboot.dto.UserRegistrationDto;
import net.ads.springboot.model.User;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}
