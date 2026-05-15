package com.msa4spring.errors;


import com.msa4spring.responses.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.List;

// ExceptionHandler 클래스 생성(클래스명은 상관없음)
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    // 예외 처리를 실행할 메소드 정의(메소드명 자유)

    // 유효성 검사 실패 예외 처리(@Vaild @Validated)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO<List<String>>> validationHandler(MethodArgumentNotValidException e
    ) {
        List<String> errorMsgList = e.getBindingResult()
            .getAllErrors()
            .stream()
            .map(ObjectError::getDefaultMessage)
            .toList();
       
        // stream이 없을경우
        // foreach로 돌리기
        // List<ObjectError> test1 = e.getBindingResult().getAllErrors();
        // List<String > list1 = null;
        // test1.forEach(item ->{
        // list.add(item.getDefaultMessage());
        // });
        
        // 향상된 for문
        // List<ObjectError> test2 = e.getBindingResult().getAllErrors();
        // List<String> list2 = new ArrayList<>(10);
        // for(ObjectError item : test2){
        // list2.add(item.getDefaultMessage());
        // }
        
        ResponseDTO<List<String >> responseDTO = ResponseDTO.<List<String>>builder()
            .code("E01")
            .message("유효성 검사 실패:")
            .data(errorMsgList)
            .build();
        
        return ResponseEntity.status(400).body(responseDTO);
    }
    // DB관련 예외 핸들러
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ResponseDTO<String>> sqlExceptionHandle(SQLException e
    ){
      log.error(e.getMessage());

        ResponseDTO<String > responseDTO = ResponseDTO.<String>builder()
            .code("E80")
            .message("DB 에러 발생")
            .data("현재 서비스 이용 불가 \n 잠시 후 다시 이용해주세요")
            .build();

        return ResponseEntity.status(500).body(responseDTO);
      
    }
    
    //
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<String>> othersHandle(Exception e){
        log.error(e.getMessage());
        
        ResponseDTO<String > responseDTO = ResponseDTO.<String>builder()
            .code("E99")
            .message("서버 에러 발생")
            .data("현재 서비스 이용 불가 \n 잠시 후 다시 이용해주세요")
            .build();
     
        return ResponseEntity.status(500).body(responseDTO);
    }
}
