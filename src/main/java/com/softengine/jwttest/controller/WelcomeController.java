package com.softengine.jwttest.controller;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.body.Body;
import com.softengine.jwttest.config.SslConfiguration;
import com.softengine.jwttest.entity.AuthRequest;
import com.softengine.jwttest.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class WelcomeController {

    @Autowired private JwtUtil jwtUtil;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private SslConfiguration sslConfiguration;

    @GetMapping("/")
    public String welcome(){
        return "monar2 dünyası";
    }

    @GetMapping("/a/a/{id}")
    public String welcomea(@RequestParam String a,@PathVariable String id){
        return "monar2 dünyası"+a+id;
    }

    @GetMapping("/a/b")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> welcomeab() throws Exception {
        RestTemplate r = sslConfiguration.restTemplate();
//        HttpResponse<JsonNode> deger = Unirest.get("https://testgrp3.sgk.intra/WS_IsverenTesvikleri/test").header("Authorization", "ewoiaW50cmFVc2VyIjoiaW50cmEiLAoiaW50cmFQYXJvbGEiOiJpbnRyYTEyMzQ1Igp9").asJson();
        Object get = r.getForObject("https://testgrp3.sgk.intra/WS_IsverenTesvikleri/test", Object.class);
        return ResponseEntity.accepted().body("(olay bu kadar,HttpStatus.OK);");
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
