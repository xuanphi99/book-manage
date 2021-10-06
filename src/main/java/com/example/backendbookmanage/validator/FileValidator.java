package com.example.backendbookmanage.validator;

import com.example.backendbookmanage.constant.Constants;
import com.example.backendbookmanage.exception.BusinessException;
import com.example.backendbookmanage.model.local.MessageCode;
import com.example.backendbookmanage.utils.CommonUtil;
import io.netty.util.internal.StringUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Slf4j
@Component
public class FileValidator {

    private String getIndexFormatFile(String s) {
        if (s != null) {
            return s.substring(s.lastIndexOf('/') + 1);
        }
        return StringUtil.EMPTY_STRING;
    }

    private String getExtentionFile(String path){
        return StringUtils.getFilenameExtension(getIndexFormatFile(path));
    }

    public void checkFileUpload (MultipartFile file){

        if (CommonUtil.isNullOrEmpty(file)){
            throw new BusinessException(MessageCode.DOGOO_05, MessageCode.DOGOO_05);
        }

        if (file.getSize() > Constants.FileInfo.maxFileSize) {
            throw new BusinessException(MessageCode.DOGOO_07);
        }
        if (!Constants.FileInfo.format.contains( getExtentionFile(file.getOriginalFilename())))
        {
            throw new BusinessException(MessageCode.DOGOO_06);
        }

    }

}
