package com.msa4spring.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserRequestMappingController {
    private String message = "기본";
    
    @GetMapping("/users")
    public String getUsers(){
        return message;
    }
    @PostMapping("/users")
    public String postUsers(){
        return "POST 유저";
    }
    @PatchMapping("/users")
    public String patchUser(@RequestBody String newMessage){
        this.message = newMessage;
        return "수정: "+this.message;
    }
    @DeleteMapping("/users")
    public String deleteUser(){
        this.message = null;
        return "삭제";
    }
}
