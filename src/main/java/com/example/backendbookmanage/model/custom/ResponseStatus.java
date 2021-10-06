package com.example.backendbookmanage.model.custom;

import com.example.backendbookmanage.model.local.TranslatorMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseStatus implements Serializable {

    public ResponseStatus(String code, boolean setMessageImplicitly) {
        setCode(code, setMessageImplicitly);
    }

    public ResponseStatus(String code, String message) {
        this.code = code;
        this.message = message;
        this.displayMessage = this.message;
    }

    public ResponseStatus(String code, String message, boolean fixDisplayMessage) {
        this.code = code;
        this.message = message;
        if (fixDisplayMessage) {
            this.displayMessage = TranslatorMessage.toMessage("message.error");
        } else {
            this.displayMessage = this.message;
        }
    }

    private String code;

    public void setCode(String code) {
        setCode(code, true);
    }

    public void setCode(String code, boolean setMessageImplicitly) {
        this.code = code;
        if (setMessageImplicitly) {
            this.message = TranslatorMessage.toMessage(code);
        }
        this.displayMessage = this.message;
    }

    public void editCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @JsonProperty("message")
    private String message;

    @JsonProperty("displayMessage")
    private String displayMessage;
}
