package com.techtalk.springwebsecurity.service;

import com.techtalk.springwebsecurity.mapper.UserDto;
import com.techtalk.springwebsecurity.model.User;
import com.techtalk.springwebsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findByEmail(String email);
    List<User> findAll();
    User findByUserName(String userName);
    User save(UserDto registration);
}
