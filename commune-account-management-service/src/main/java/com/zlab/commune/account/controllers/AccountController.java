package com.zlab.commune.account.controllers;

import com.zlab.commune.account.response.UserRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private Environment env;

    @GetMapping("/status/check")
    public String status(){

        return "Working on port: "+env.getProperty("local.server.port");
    }

    @PreAuthorize("hasAuthority('ROLE_developer') and #id == #jwt.subject")
    //@Secured("ROLE_user")
    @DeleteMapping(path = "/{id}")
    public String deleteUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt){

        return "Deleted Account ID: "+ id +" and JWT Subject "+jwt.getSubject();
    }

    @PostAuthorize("returnObject.userId == #jwt.subject")
    @GetMapping(path = "/{id}")
    public UserRest getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt){

        return new UserRest("Zubair", "Abubakar", "ffe3d1eb-08cf-4010-911f-3f7d06c4d5db");
    }
}
