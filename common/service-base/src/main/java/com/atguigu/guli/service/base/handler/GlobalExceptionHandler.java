package com.atguigu.guli.service.base.handler;

import com.atguigu.guli.common.base.result.R;
import com.atguigu.guli.common.base.result.ResultCodeEnum;
import com.atguigu.guli.common.base.util.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    //所有controller层抛出的异常都会在此捕获
    //捕获Exception异常对象和Exception的子类异常
    @ExceptionHandler(Exception.class)
    @ResponseBody//返回R对象的json形式
    public R error(Exception e){
        //打印异常跟踪栈，在控制台中显示异常跟踪信息
        e.printStackTrace();
        log.error(ResultCodeEnum.JSON_PARSE_ERROR.toString());
        log.error(ExceptionUtils.getMessage(e));
        //返回异常结果的r对象
        return R.error();
    }
    //捕获特定异常对象BadSqlGrammarException
    @ExceptionHandler(BadSqlGrammarException.class)
    @ResponseBody//返回R对象的json形式
    public R error(BadSqlGrammarException e){
        System.out.println("BadSqlGrammarException");
        //打印异常跟踪栈，在控制台中显示异常跟踪信息
        e.printStackTrace();
        //遵循日志记录器的配置输出日志
        log.error(ResultCodeEnum.JSON_PARSE_ERROR.toString());
        log.error(ExceptionUtils.getMessage(e));
        //返回异常结果的r对象
        return R.setResult(ResultCodeEnum.BAD_SQL_GRAMMAR);
    }
    //捕获特定异常对象HttpMessageNotReadableException
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody//返回R对象的json形式
    public R error(HttpMessageNotReadableException e){
        System.out.println("HttpMessageNotReadableException");
        //打印异常跟踪栈，在控制台中显示异常跟踪信息
        e.printStackTrace();
        log.error(ResultCodeEnum.JSON_PARSE_ERROR.toString());
        log.error(ExceptionUtils.getMessage(e));
        //返回异常结果的r对象
        return R.setResult(ResultCodeEnum.JSON_PARSE_ERROR);
    }

    //自定义异常处理
    //使用一个异常处理方式，处理所有的异常信息，并显示个性化的异常结果
}
