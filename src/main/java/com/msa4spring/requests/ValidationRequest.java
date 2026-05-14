package com.msa4spring.requests;

import jakarta.validation.constraints.*;

public record ValidationRequest(
    @NotBlank(message = "아이디 필수")
    String email,
    
    @NotBlank(message = "비밀번호 필수")
    @Size(min = 5, max = 20, message = "비밀번호 5~20 허용")
    String password,
    
    @NotNull(message = "나이 필수")
    @Min(0)
    @Max(200)
    Integer age,
    
    @NotBlank(message = "이름 필수")
    @Pattern(regexp = "^[가-힣]{2,50}$",message = "한글 2~50글자 사이")
    String name
) {
}
