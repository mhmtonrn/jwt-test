package com.softengine.jwttest.controller;

import com.softengine.jwttest.entity.AuthRequest;
import com.softengine.jwttest.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasRole('ADMIN')")
public class WelcomeController {

    @Autowired private JwtUtil jwtUtil;
    @Autowired private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String welcome(){
        return "monar2 dünyası";
    }

    @GetMapping("/a/a/{id}")
    public String welcomea(@RequestParam String a,@PathVariable String id){
        return "monar2 dünyası"+a+id;
    }

    @GetMapping("/a/b")
    public String welcomeab(){
        return "monar2 dünyası";
    }


    @PostMapping("/auth")
    public String generateToken(@RequestBody AuthRequest authRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(),authRequest.getPassword()));
        return jwtUtil.generateToken(authRequest.getUserName());
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(),authRequest.getPassword()));
        return jwtUtil.generateToken(authRequest.getUserName());
    }
}
