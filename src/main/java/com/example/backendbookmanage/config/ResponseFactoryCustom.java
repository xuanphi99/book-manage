package com.example.backendbookmanage.config;


import com.example.backendbookmanage.model.custom.GeneralResponseCustom;
import com.example.backendbookmanage.model.custom.Paging;
import com.example.backendbookmanage.model.custom.ResponseStatus;
import com.example.backendbookmanage.model.local.ResponseStatusCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;

@Component
@Slf4j
public class ResponseFactoryCustom {
    @Autowired
    private MessageResponseConfig messageResponseConfig;

    private String replaceParams(String message, Map<String, String> params) {
        // replace params in message
        if (!CollectionUtils.isEmpty(params)) {
            for (Map.Entry<String, String> param : params.entrySet()) {
                message = message.replaceAll("%%" + param.getKey() + "%%", param.getValue());
            }
        }

        // replace general params
        if (messageResponseConfig != null && !CollectionUtils.isEmpty(messageResponseConfig.getParams())) {
            for (Map.Entry<String, String> param : messageResponseConfig.getParams().entrySet()) {
                message = message.replaceAll("%%" + param.getKey() + "%%", param.getValue());
            }
        }
        return message;
    }

    private ResponseStatus parseResponseStatus(String code, Map<String, String> params) {
        ResponseStatus responseStatus = new ResponseStatus(code, true);
        responseStatus.setMessage(replaceParams(responseStatus.getMessage(), params));
        responseStatus.setDisplayMessage(responseStatus.getMessage());
        return responseStatus;
    }

    /**
     * Response with status code equals SUCCESS
     */
    public <T> ResponseEntity<GeneralResponseCustom<T>> success(T data) {
        GeneralResponseCustom<T> responseObject = new GeneralResponseCustom<>();
        responseObject.setData(data);
        ResponseStatus responseStatus = parseResponseStatus(ResponseStatusCodeEnum.SUCCESS.getCode(), null);
        responseObject.setStatus(responseStatus);
        return ResponseEntity.ok(responseObject);
    }

    public <T> ResponseEntity<GeneralResponseCustom<T>> success(T data, Paging paging) {
        GeneralResponseCustom<T> responseObject = new GeneralResponseCustom<>();
        responseObject.setData(data);
        responseObject.setPaging(paging);

        ResponseStatus responseStatus = parseResponseStatus(ResponseStatusCodeEnum.SUCCESS.getCode(), null);
        responseObject.setStatus(responseStatus);

        return ResponseEntity.ok(responseObject);
    }

}
