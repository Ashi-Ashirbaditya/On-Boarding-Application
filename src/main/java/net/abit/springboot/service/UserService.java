package net.abit.springboot.service;

import net.abit.springboot.dto.UserRegistrationDto;
import net.abit.springboot.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}
