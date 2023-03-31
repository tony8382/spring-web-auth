package com.lyyang.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {
    private static final String SCOPE_AUTHORITY_PREFIX = "ROLE_";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                List.of("AAA", "GGG", "BB").stream().map(authority -> SCOPE_AUTHORITY_PREFIX + authority.toUpperCase())
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList()));


        if (user == null) {
            log.warn("User Not Found with username : " + username);
            throw new UsernameNotFoundException("User Not Found with username : " + username);
        }
        return user;
    }

}