package com.example.backend.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

public class Constant {

    public static Gson gson = new GsonBuilder().setDateFormat(Common.COMMON_DATETIME_FORMAT).excludeFieldsWithModifiers(Modifier.STATIC).create();
    public static String ERROR_CODE = "-1";

    public static class Common {
        public static final String REGISTER_ROLE = "[{\"id\":1,\"name\":\"ROLE_ADMIN\"}]";
        public static final String COMMON_DATETIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
    }

    public static class RULE_STUDENT {
        public static String HANH_KIEM_TOT = "tot";
        public static String HANH_KIEM_KHA = "kha";
        public static String HANH_KIEM_TRUNG_BINH = "trung binh";
        public static String HANH_KIEM_YEU = "yeu";

        public static String HOC_LUC_GIOI = "gioi";
        public static String HOC_LUC_KHA = "kha";
        public static String HOC_LUC_TRUNG_BINH = "trung binh";
        public static String HOC_LUC_YEU = "yeu";
    }

    public static class STATUS {
        public static String REJECT = "REJECT";
        public static String PASS = "PASS";
        public static String DELETE = "DELETE";
    }

    public static class REASON_REJECT {
        public static String AGE_NOT_VALID = "Tuổi không hợp lệ";
        public static String CONDUCT_NOT_VALID = "Hạnh kiểm không hợp lệ!";
        public static String ACADEMIC_NOT_VALID = "Học lực không hợp lệ";
        public static String PARTICIPATION_NOT_VALID = "Đã nhiều lần tham gia trong năm nay";
    }
}
