package com.codetreatise.common;

public class ErrorCodes {

    public static final Integer GENERAL_CUSTOM = 100;
    public static final Integer GENERAL_MISSING = 101;
    public static final Integer GENERAL_INVALID = 102;
    public static final Integer GENERAL_NOT_FOUND = 103;


    public static final ServiceError getServiceError(Integer errorCode,String message) {
        switch (errorCode) {
            case 100:
                return new ServiceError(GENERAL_CUSTOM,message);
            case 101:
                return new ServiceError(GENERAL_MISSING,String.format("Required parameter missing for:%s",message));
            case 102:
                return new ServiceError(GENERAL_INVALID,String.format("Invalid value for:%s",message));
            case 103:
                return new ServiceError(GENERAL_NOT_FOUND,String.format("Value not found for:%s",message));
            default:
                return new ServiceError(errorCode,message);
        }
    }
}
