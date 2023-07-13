package com.codetreatise.common;

import com.codetreatise.dto.*;
import com.codetreatise.entity.EnrollAuthority;

import java.util.ArrayList;
import java.util.List;

public class Defs {

    public static OnlineApplicationResponse ONLINE = null;
    public static List<Division> divisionsLookup = new ArrayList<>();
    public static List<District> districtsLookup = new ArrayList<>();
    public static List<Thana> thanasLookup = new ArrayList<>();
    public static EnrollAuthority AUTHORITY = null;
    public static List<Authority> AUTHORITY_LIST = new ArrayList<>();
    public static String CIRCLE = null;
    public static final String ALL = "ALL";

    public static final String GENDER_MALE = "MALE";
    public static final String GENDER_FEMALE = "FEMALE";
    public static final String GENDER_THIRD = "THIRD";

    public static final String OPERATION_SUCCESS = "SUCCESS";
    public static final String OPERATION_FAILED = "FAILED";

    public static String LOGGED_USER = null;

    public static final String DATE_FORMAT = "dd-MM-yyyy";

    public static Integer APPLICATION_ID = null;
    public static String APPLICATION_TYPE = null;

    public static final String SIX_SEATER_AUTO = "6 SEATER AUTO";

    public static final String TWO_SEATER_AUTO = "2 SEATER AUTO";
    public static final String MANUAL_RICKSHAW = "MANUAL RICKSHAW";
    public static final String BATTERY_RICKSHAW = "BATTERY RICKSHAW";

    public static final String LOGIN_LINK = "http://40.90.232.155/api/auth/token";
    public static final String UPLOAD_LINK = "http://40.90.232.155/api/application/enroll";
    public static final String ONLINE_ENROLL_LINK ="http://40.90.232.155/api/application/online-application/";

    public static final void prepareList(List<com.codetreatise.entity.Division> divisions,List<com.codetreatise.entity.District> districts,List<com.codetreatise.entity.Thana> thanas) {
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
