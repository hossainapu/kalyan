package com.hellokoding.auth.common;


import com.hellokoding.auth.model.dto.District;
import com.hellokoding.auth.model.dto.Division;
import com.hellokoding.auth.model.dto.Thana;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Defs {

    public static List<Division> divisionsLookup = new ArrayList<>();
    public static List<District> districtsLookup = new ArrayList<>();
    public static List<Thana> thanasLookup = new ArrayList<>();
    public static String CIRCLE = null;
    public static final String ALL = "ALL";

    public static final String GENDER_MALE = "পুরুষ";
    public static final String GENDER_FEMALE = "মহিলা";
    public static final String GENDER_THIRD = "তৃতীয়";

    public static final String OPERATION_SUCCESS = "SUCCESS";
    public static final String OPERATION_FAILED = "FAILED";

    public static String LOGGED_USER = null;

    public static final String DATE_FORMAT = "dd/MM/yyyy";

    public static Integer APPLICATION_ID = null;
    public static String APPLICATION_TYPE = null;

    public static final String SIX_SEATER_AUTO = "6 SEATER AUTO";
    public static final String THREE_SEATER_AUTO = "3 SEATER AUTO";
    public static final String MANUAL_RICKSHAW = "MANUAL RICKSHAW & VAN";
    public static final String BATTERY_RICKSHAW = "BATTERY RICKSHAW & VAN";

    public static final String LOGIN_LINK = "http://139.59.45.81:8080//api/auth/token";
    public static final String UPLOAD_LINK = "http://139.59.45.81:8080/api/application/enroll";

    public static final Map<String,String> APPLICATION_TYPE_MAP = new HashMap<>();
    public static final Map<String,String> BLOOD_GROUP_MAP = new HashMap<>();
    public static final Map<String,String> GENDER_MAP = new HashMap<>();

    public static final String BLOOD_GROUP_A_POSITIVE = "এ+";
    public static final String BLOOD_GROUP_A_NEGATIVE = "এ-";
    public static final String BLOOD_GROUP_B_POSITIVE = "বি+";
    public static final String BLOOD_GROUP_B_NEGATIVE = "বি-";
    public static final String BLOOD_GROUP_AB_POSITIVE ="এবি+";
    public static final String BLOOD_GROUP_AB_NEGATIVE = "এবি-";
    public static final String BLOOD_GROUP_O_POSITIVE = "ও+";
    public static final String BLOOD_GROUP_O_NEGATIVE = "ও-";

    public static final String SMS_URL = "https://sms.sslwireless.com/pushapi/dynamic/server.php";
    public static final String SID_EN = "EurotelNONEng";
    public static final String SID_BN = "EurotelNONBng";
    public static final String SMS_USER= "EurotelBusinessSystem";
    public static final String SMS_PASSWORD = "Abc@1234";

    static {
        APPLICATION_TYPE_MAP.put("6_S_A",SIX_SEATER_AUTO);
        APPLICATION_TYPE_MAP.put("3_S_A",THREE_SEATER_AUTO);
        APPLICATION_TYPE_MAP.put("M_R_V",MANUAL_RICKSHAW);
        APPLICATION_TYPE_MAP.put("B_R_V",BATTERY_RICKSHAW);

        BLOOD_GROUP_MAP.put(BLOOD_GROUP_A_POSITIVE,BLOOD_GROUP_A_POSITIVE);
        BLOOD_GROUP_MAP.put(BLOOD_GROUP_A_NEGATIVE,BLOOD_GROUP_A_NEGATIVE);
        BLOOD_GROUP_MAP.put(BLOOD_GROUP_B_POSITIVE,BLOOD_GROUP_B_POSITIVE);
        BLOOD_GROUP_MAP.put(BLOOD_GROUP_B_NEGATIVE,BLOOD_GROUP_B_NEGATIVE);
        BLOOD_GROUP_MAP.put(BLOOD_GROUP_AB_POSITIVE,BLOOD_GROUP_AB_POSITIVE);
        BLOOD_GROUP_MAP.put(BLOOD_GROUP_AB_NEGATIVE,BLOOD_GROUP_AB_NEGATIVE);
        BLOOD_GROUP_MAP.put(BLOOD_GROUP_O_POSITIVE,BLOOD_GROUP_O_POSITIVE);
        BLOOD_GROUP_MAP.put(BLOOD_GROUP_O_NEGATIVE,BLOOD_GROUP_O_NEGATIVE);

        GENDER_MAP.put(GENDER_MALE,GENDER_MALE);
        GENDER_MAP.put(GENDER_FEMALE,GENDER_FEMALE);
        GENDER_MAP.put(GENDER_THIRD,GENDER_THIRD);

    }

    public static final void prepareList(List<com.hellokoding.auth.model.db.Division> divisions,List<com.hellokoding.auth.model.db.District> districts,List<com.hellokoding.auth.model.db.Thana> thanas) {
        if(divisions != null) {
            divisionsLookup.clear();
            divisions.stream().forEach(div->{
                divisionsLookup.add(new Division(div));
            });
        }
        if(districts != null) {
            districts.stream().forEach(dis->{
                districtsLookup.add(new District(dis));
            });
        }

        if(thanas != null) {
            thanas.stream().forEach(thana -> {
                thanasLookup.add(new Thana(thana));
            });
        }
    }



}
