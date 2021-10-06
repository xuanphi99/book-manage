package com.example.backendbookmanage.model.local;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseStatusCodeEnum {
    SUCCESS("00", 200),
    BUSINESS_ERROR("DOGOO-01", 400),
    VALIDATION_ERROR("DOGOO-01", 204);
    private String code;
    private int httpCode;


}
