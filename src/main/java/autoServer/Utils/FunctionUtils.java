package autoServer.Utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
    public static LocalDateTime parseToLocalDatetime(Object value, String format) {
        if (value == null)
            return null;
        String tmp = parseToString(value);
        if (tmp == null)
            return null;

        try {
            LocalDateTime rtn = convertStringToLocalDateTime(tmp, format);
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
    public static Date convertStringToDate(String date) throws ParseException {
        return convertStringToDate(date,"yyyy-MM-dd");
    }

    public static Date convertStringToDate(String date, String patten) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(patten);
        return format.parse(date);
    }
}
