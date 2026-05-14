package com.msa4spring.requests;

public record UsersPaginationRequest(
    Integer page
    ,Integer limit
) {
//    public UsersPaginationRequest(String page, String limit){
//        this.page = (page == null || page.isBlank()) ? "1" : page;
//        this.limit = (limit == null || limit.isBlank()) ? "10" : limit;}
    public UsersPaginationRequest(Integer page, Integer limit){
       this.page = (page == null ) ? 1 : page;
       this.limit = (limit == null ) ? 10 : limit;}
}

