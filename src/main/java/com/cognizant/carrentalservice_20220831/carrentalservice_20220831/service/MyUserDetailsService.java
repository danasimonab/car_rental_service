package com.cognizant.carrentalservice_20220831.carrentalservice_20220831.service;

import com.cognizant.carrentalservice_20220831.carrentalservice_20220831.model.User;
import com.cognizant.carrentalservice_20220831.carrentalservice_20220831.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
        //.orElseThrow(() -> new UsernameNotFoundException("User" + username + "not found!"));
    }
}
