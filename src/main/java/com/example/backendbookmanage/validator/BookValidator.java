package com.example.backendbookmanage.validator;

import com.example.backendbookmanage.exception.BusinessException;
import com.example.backendbookmanage.model.dto.BookDto;
import com.example.backendbookmanage.model.local.MessageCode;
import com.example.backendbookmanage.utils.CommonUtil;
import org.springframework.stereotype.Component;

@Component
public class BookValidator {
    public void checkEmpty(BookDto bookDto){
        if (bookDto.getImgName().isEmpty()|| bookDto.getImgUrl().isEmpty()){
            throw new BusinessException(MessageCode.DOGOO_05, MessageCode.DOGOO_05);
        }
        if (CommonUtil.isNullOrEmpty(bookDto.getCategoryId()) )
            throw new BusinessException(MessageCode.DOGOO_08, MessageCode.DOGOO_08);

        if(CommonUtil.isNullOrEmpty(bookDto.getAuthorDtoList()))
            throw new BusinessException(MessageCode.DOGOO_09, MessageCode.DOGOO_09);

        if(CommonUtil.isNullOrEmpty(bookDto.getPublisherId()))
            throw new BusinessException(MessageCode.DOGOO_10, MessageCode.DOGOO_10);

        if(CommonUtil.isNullOrEmpty(bookDto.getDescription()) ||CommonUtil.isNullOrEmpty(bookDto.getTitle())
        )
            throw new BusinessException(MessageCode.DOGOO_11, MessageCode.DOGOO_11);

    }
}
