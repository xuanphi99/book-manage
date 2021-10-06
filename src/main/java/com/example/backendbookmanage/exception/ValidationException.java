package com.example.backendbookmanage.exception;

import com.example.backendbookmanage.model.local.TranslatorMessage;
import lombok.Data;

@Data
public class ValidationException extends RuntimeException {

    private final String code;

    public ValidationException(String code) {
        super(TranslatorMessage.toMessage(code));
        this.code = code;
    }

    public ValidationException(String code, String messageKey) {
        super(TranslatorMessage.toMessage(messageKey));
        this.code = code;
    }

    public ValidationException(String code, String messageKey, Object... args) {
        super(TranslatorMessage.toMessage(messageKey, args));
        this.code = code;
    }
}
