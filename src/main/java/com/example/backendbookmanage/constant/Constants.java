package com.example.backendbookmanage.constant;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * Create file constants
 * @author PhiDX
 * @version v1
 * @createDate 21/09/2021 : 12:52 AM
 * @modifyBy
 * @updateDate
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
    public static final String PATH_MESSAGE = "messages";
    public static final String APP_PATH_BASE_PUBLIC = "/dogoo/api/v1/private";

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class PathMapping {
    	public static final String API_BOOK = "/books";
    	public static final String API_CATEGORY = "/categories";
    	public static final String API_AUTHOR = "/authors";
        public static final String API_PUBLISHER = "/publishers";


    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class FormatDate {

        public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
        public static final String YYYY_MM_DD = "yyyy-MM-dd";
        public static final String YYYY_MMDD_HHMMSS = "yyyy/MM/dd HH:mm:ss";
        public static final String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";
        public static final String DDMMYYYY = "dd/MM/yyyy";
        public static final String DD_MM_YY = "dd-MM-yyyy";
        public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
        public static final String YYYYMMDD = "yyyyMMdd";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class RequestStatus {

        public static final String TIMEOUT = "605";
        public static final String PAY_SUCCESS = "0006";
        public static final String FAIL = "0008";

    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ResponseCode {

        public static final String SUCCESS = "00";
        public static final String TIMEOUT_INTERNAL = "605";
        public static final String TIMEOUT_EXTERNAL = "650";
        public static final String TIMEOUT = "32";
        public static final String FAILURE = "23";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class FileInfo {

        public static final List<String> format = Arrays.asList("png","jpg","jpeg");
        public static final long maxFileSize = 1 * 1024 * 1024;
    }

}
