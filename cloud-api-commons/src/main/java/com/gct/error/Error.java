package com.gct.error;

import com.gct.result.ResultData;
import com.gct.result.ReturnCodeEnum;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Error {

    @ExceptionHandler(ArithmeticException.class)
    public ResultData ArithmeticException(Exception e){
        return ResultData.fail(ReturnCodeEnum.RC375);
    }

}
