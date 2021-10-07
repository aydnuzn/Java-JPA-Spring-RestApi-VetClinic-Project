package com.works.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Util {

    public static boolean isEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public static boolean isTel(String tel) {
        String regex = "^\\d{10}$";
        return tel.matches(regex);
    }

    public static final Integer pageSize = 10;

    public static List<Map<String, String>> errors(BindingResult bResult) {
        List<Map<String, String>> ls = new LinkedList<>();

        bResult.getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String fieldMessage = error.getDefaultMessage();

            Map<String, String> erhm = new HashMap<>();
            erhm.put("fieldName", fieldName);
            erhm.put("fieldMessage", fieldMessage);
            ls.add(erhm);
        });
        return ls;
    }

    public static final String string = "java.lang.String";
    public static final String integer = "java.lang.Integer";
    public static final String myBoolean = "java.lang.Boolean";
    public static final String date = "java.util.Date";
    public static final String myArray = "java.util.Arrays";

    public static final String UPLOAD_DIR = "src/main/resources/static/uploads/";
}
