package com.example.backendbookmanage.exception;

import com.example.backendbookmanage.model.local.TranslatorMessage;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final String code;

    public BusinessException(String code) {
        super(TranslatorMessage.toMessage(code));
        this.code = code;
    }

    public BusinessException(String code, String messageKey) {

        super(TranslatorMessage.toMessage(messageKey));
        this.code = code;

    }

    public BusinessException(String code, String messageKey, Object... args) {
        super(TranslatorMessage.toMessage(messageKey, args));
        this.code = code;
    }


}
