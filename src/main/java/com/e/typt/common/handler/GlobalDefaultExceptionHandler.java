package com.e.typt.common.handler;


import com.e.typt.common.bean.ResponseResult;
import com.e.typt.common.bean.ResponseCode;
import com.e.typt.common.exception.RequestException;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 */
@ControllerAdvice(basePackages = {"com.e.typt"})
@Slf4j
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(value = RequestException.class)
    @ResponseBody
    public ResponseResult requestExceptionHandler(RequestException e){
        if(e.getE()!=null) e.printStackTrace();
        return ResponseResult.builder().msg(e.getMsg()).status(e.getStatus()).build();
    }


    @ExceptionHandler(value = DataIntegrityViolationException.class)
    @ResponseBody
    public ResponseResult requestExceptionHandler(DataIntegrityViolationException e){
        return ResponseResult.builder().msg("数据操作格式异常").status(ResponseCode.FAIL.code).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        BindingResult result = e.getBindingResult();
        String s = "参数验证失败";
        if(result.hasErrors()){
            List<ObjectError> errors = result.getAllErrors();
            s = errors.get(0).getDefaultMessage();
        }
        return ResponseResult.builder().status(ResponseCode.FAIL.code).msg(s).build();
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseResult requestExceptionHandler(Exception e){
        e.printStackTrace();
        return ResponseResult.builder().msg("服务器飘了，管理员去拿刀修理了~").status(ResponseCode.FAIL.code).build();
    }






}
