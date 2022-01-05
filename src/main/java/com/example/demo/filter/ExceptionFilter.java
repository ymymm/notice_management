package com.example.demo.filter;

import com.example.demo.common.ApiCommonCode;
import com.example.demo.common.ApiException;
import com.example.demo.exception.FileException;
import com.example.demo.exception.NoticeException;
import com.example.demo.exception.TokenException;
import com.example.demo.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionFilter {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ApiException validException(MethodArgumentNotValidException e) {
        ApiException exception = ApiException.builder().status(ApiCommonCode.PARAM_ERROR).message(e.getBindingResult().getAllErrors().get(0).getDefaultMessage()).description("test").build();

        log.error("==== [Param error] : {} ====", exception.getMessage());
        return exception;
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserException.class)
    public ApiException handleUserException(UserException e) {
        ApiException exception = ApiException.builder().status(e.getApiCommonCode()).message(e.getMessage())
                .description(e.getApiCommonCode().getDescription()).build();

        log.error("==== [{}] : {} ====", exception.getStatus(), exception.toString());
        return exception;
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(FileException.class)
    public ApiException handleFileException(UserException e) {
        ApiException exception = ApiException.builder().status(e.getApiCommonCode()).message(e.getMessage())
                .description(e.getApiCommonCode().getDescription()).build();

        log.error("==== [{}] : {} ====", exception.getStatus(), exception.toString());
        return exception;
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(TokenException.class)
    public ApiException handleTokenException(TokenException e) {
        ApiException exception = ApiException.builder().status(e.getApiCommonCode()).message(e.getMessage())
                .description(e.getApiCommonCode().getDescription()).build();

        log.error("==== [{}] : {} ====", exception.getStatus(), exception.toString());
        return exception;
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NoticeException.class)
    public ApiException handleNoticeException(NoticeException e) {
        ApiException exception = ApiException.builder().status(e.getApiCommonCode()).message(e.getMessage())
                .description(e.getApiCommonCode().getDescription()).build();

        log.error("==== [{}] : {} ====", exception.getStatus(), exception.toString());
        return exception;
    }
}
