package com.aspire.userservice.util;

import com.aspire.userservice.exception.UserServiceException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class DateUtil {
    private DateUtil() {

    }

    private static final String DATE_FORMATE = "dd-MM-yyyy";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMATE);

    public static String formate(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return localDate.format(DATE_FORMATTER);
    }

    public static LocalDate parse(String date){
        if (date == null || date.isEmpty() || date.isBlank()){
            return null;
        }
        try{
            return LocalDate.parse(date , DATE_FORMATTER);
        }catch (DateTimeParseException e){
            throw new UserServiceException("Invalid Date formate and excpected formate is : " +DATE_FORMATE);
        }
    }

    public static LocalDate today(){
        return LocalDate.now();
    }


}
