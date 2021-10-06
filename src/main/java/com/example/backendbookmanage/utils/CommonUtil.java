package com.example.backendbookmanage.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonUtil {

    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }
        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        }
        if (obj instanceof MultipartFile) {
            return ((MultipartFile) obj).isEmpty() ;
        }
        return false;
    }
    public static boolean nullOrEmpty(Collection objects) {
        return objects == null || objects.isEmpty();
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean notNull(Object obj) {
        return !isNull(obj);
    }

    public static boolean nullOrEmpty(LocalDateTime input) {
        return input == null || input.toString().trim().isEmpty();
    }

    public static boolean notNullOrEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }


    public static LocalDateTime convertStringToLocalDateTime(String value, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        if (value == null || value.trim().isEmpty()) {
            return null;
        } else if (value.contains(".")) {
            value = value.substring(0, value.indexOf('.'));
        }
        return LocalDateTime.parse(value, formatter);
    }
    public static String localDateTimeToString(LocalDateTime value, String format) {
        if (!notNull(value)) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return value.format(formatter); // "1986-04-08 12:30"
    }

    public static String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }

    public static File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }


}
