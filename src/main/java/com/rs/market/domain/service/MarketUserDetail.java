package com.rs.market.domain.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;

@Service
public class MarketUserDetail implements UserDetailsService {

    @Value("${user.detail.username}")
    private  String usernames;

    @Value("${user.detail.password}")
    private  String passwords;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return new User(usernames, "{noop}"+passwords, new ArrayList<>());
    }
}
