package com.msa4spring.controllers;

import com.msa4spring.requests.PostsFilterRequest;
import com.msa4spring.requests.UsersPaginationRequest;
import org.apache.catalina.users.SparseUserDatabase;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserRequestMappingController {
    private String message = "기본";
    
    @GetMapping("/users")
    public String getUsers(
        @RequestParam(required = false, defaultValue = "1") String page
        ,@RequestParam String limit
        // 쿼리 파라미터 획득 방법 : @RequesParam 어노테이션을 통해 획득
    ){
        return message + page + ", " + limit;
    }
    @GetMapping("/users/{key}")
    public String show(
        @PathVariable String key
    ){
        return "GET show" + ", " + key ;
    }

    // -------------------------
    // DTO를 활용하여 파라미터 획득
    // -------------------------
    @GetMapping("/users/dto-param")
    public String dtoParam(
        UsersPaginationRequest usersPaginationRequest
    ){
        return String.format("GET dtoParam: %d %d"
            , usersPaginationRequest.page()
            ,usersPaginationRequest.limit());
    }
    
    // -------------------------
    //  세그먼트 파라미터 || Form Data를 DTO로 획득
    // @ModelAttribute를 파라미터에
    // -------------------------
    @GetMapping("/posts/{id}/filter/{categoryId}")
    public String postFilter(
        @ModelAttribute PostsFilterRequest postsFilterRequest){
        return String.format("postFilter: %d, %d"
            ,postsFilterRequest.id()
            ,postsFilterRequest.categoryId());
    }
    
    // JSON 데이터를 DTO로 획득
    @GetMapping("/posts/json")
    public String postsJson(
        @RequestBody PostsFilterRequest postsFilterRequest
    ){
        return String.format("postJson: %d, %d"
            ,postsFilterRequest.id()
            ,postsFilterRequest.categoryId()); 
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
