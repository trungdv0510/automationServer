package autoServer.Utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class FunctionUtils {
    public static boolean checkIsNull(Object data){
        return data != null;
    }

    public static Long parseToLong(Object value){
        try {
            String valueStr = parseToString(value);
            long valueLong = Long.parseLong(valueStr);
            return valueLong;
        }catch (Exception e){
            log.info(e.getMessage());
            return null;
        }
    }

    public static String parseToString(Object str){
        if (checkIsNull(str)){
            return String.valueOf(str);
        }
        return "";
    }
    public static boolean isObjectNull(Object obj) {
        return obj == null;
    }

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().equals("");
    }
    public static LocalDateTime parseToLocalDatetime(Object value) {
        if (value == null)
            return null;
        String tmp = parseToString(value);
        if (tmp == null)
            return null;

        try {
            LocalDateTime rtn = convertStringToLocalDateTime(tmp, "yyyy-MM-dd HH:mm:ss.S");
            return rtn;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return null;
    }
    public static LocalDateTime convertStringToLocalDateTime(String value, String fomart) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(fomart);
        LocalDateTime formatDateTime = LocalDateTime.parse(value, formatter);
        return formatDateTime;
    }

    public static LocalDateTime convertStringToLocalDateTime(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime formatDateTime = LocalDateTime.parse(value, formatter);
        return formatDateTime;
    }
}
