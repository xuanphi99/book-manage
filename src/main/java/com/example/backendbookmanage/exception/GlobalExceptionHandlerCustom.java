package com.example.backendbookmanage.exception;

import com.example.backendbookmanage.model.custom.GeneralResponseCustom;
import com.example.backendbookmanage.model.custom.ResponseStatus;
import com.example.backendbookmanage.model.local.MessageCode;
import com.example.backendbookmanage.model.local.ResponseStatusCodeEnum;
import com.example.backendbookmanage.model.local.TranslatorMessage;
import com.example.backendbookmanage.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.io.IOException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandlerCustom {

    @ExceptionHandler({Exception.class, IOException.class, MissingServletRequestPartException.class})
    public final ResponseEntity<GeneralResponseCustom<Object>> handleAllExceptions(Exception ex) {
        log.error(ex.getMessage(), ex);
        return createResponse(ResponseStatusCodeEnum.BUSINESS_ERROR.getCode(),
                ResponseStatusCodeEnum.BUSINESS_ERROR.getHttpCode(),
                TranslatorMessage.toMessage(MessageCode.DOGOO_99));
    }

    @ExceptionHandler({BusinessException.class})
    public final ResponseEntity<GeneralResponseCustom<Object>> handleBusinessException(BusinessException ex) {
        log.error(ex.getMessage(), ex);
        String code = ex.getCode();
        if (!CommonUtil.isNullOrEmpty(code)) {
            return createResponseWithMessage(
                    code,
                    ResponseStatusCodeEnum.BUSINESS_ERROR.getHttpCode(),
                    ex.getMessage());
        }

        return createResponse(
                ResponseStatusCodeEnum.BUSINESS_ERROR.getCode(),
                ResponseStatusCodeEnum.BUSINESS_ERROR.getHttpCode(),
                ex.getMessage());
    }



    private ResponseEntity<GeneralResponseCustom<Object>> createResponseWithMessage(String code, Integer httpStatus, String message) {
        ResponseStatus responseStatus = new ResponseStatus(code, message, false);
        GeneralResponseCustom<Object> responseObject = new GeneralResponseCustom<>();
        responseObject.setStatus(responseStatus);
        return new ResponseEntity<>(responseObject, HttpStatus.valueOf(httpStatus));
    }


    private ResponseEntity<GeneralResponseCustom<Object>> createResponse(String code, Integer httpStatus, String message) {
        ResponseStatus responseStatus = new ResponseStatus(code, true);
        if (CommonUtil.isNullOrEmpty(responseStatus.getMessage())) {
            responseStatus.setMessage(message);
        }
        if (!CommonUtil.isNullOrEmpty(message)) {
            responseStatus.setDisplayMessage(message);
        } else {
            responseStatus.setDisplayMessage(TranslatorMessage.toMessage(code));
        }
        GeneralResponseCustom<Object> responseObject = new GeneralResponseCustom<>();
        responseObject.setStatus(responseStatus);
        return new ResponseEntity<>(responseObject, HttpStatus.valueOf(httpStatus));
    }
}
