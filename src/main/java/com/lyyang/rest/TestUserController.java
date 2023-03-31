package com.lyyang.rest;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("test")
public class TestUserController {

    @GetMapping("get")
    public String currentUserName(Authentication authentication) {
        return authentication.getName();
    }

    @Secured("AAA")
    @GetMapping("t1")
    public List<String> t1(Authentication authentication) {
        return authentication.getAuthorities().stream().map(i -> i.getAuthority()).collect(Collectors.toList());
    }

    @Secured("ROLE_AAA")
    @GetMapping("t2")
    public List<String> t2(Authentication authentication) {
        return authentication.getAuthorities().stream().map(i -> i.getAuthority()).collect(Collectors.toList());
    }

    @Secured("ROLE_BBB")
    @GetMapping("t3")
    public List<String> t3(Authentication authentication) {
        return authentication.getAuthorities().stream().map(i -> i.getAuthority()).collect(Collectors.toList());
    }
}