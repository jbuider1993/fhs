package com.fhs.core.valid.aop;

import com.fhs.core.result.HttpResult;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Map;
import java.util.Set;

/**
 * 参数异常处理
 *
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.core.validation
 * @ClassName: ParamExceptionHandlerAdvice
 * @Author: JackWang
 * @CreateDate: 2018/11/2 0002 14:43
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/11/2 0002 14:43
 * @Version: 1.0
 */
@RestControllerAdvice
public class ParamExceptionHandlerAdvice {


    /**
     * 请求的 JSON 参数在请求体内的参数校验
     *
     * @param e 异常信息
     * @return 返回数据
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResult<Map> bindException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder errorMesssage = new StringBuilder("校验失败:");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssage.append(fieldError.getField() + ":" + fieldError.getDefaultMessage() + ", ");
        }
        return HttpResult.validateError(null, errorMesssage.toString());
    }


    /**
     * 请求的 URL 参数检验
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResult<Map> bindViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> bindingResults = e.getConstraintViolations();
        StringBuilder errorMesssage = new StringBuilder("校验失败:");
        int size = 0;
        for (ConstraintViolation bindingResult : bindingResults) {
            errorMesssage.append(bindingResult.getPropertyPath() + ":" + bindingResult.getMessage());
            if (size != (bindingResults.size() - 1)) {
                errorMesssage.append(",");
            }
            size++;
        }
        return HttpResult.validateError(null, errorMesssage.toString());
    }
}
