package com.example.polls.util;

import java.io.Serializable;

public class ErrorCodes implements Serializable {

    public static final Integer ERROR_GENERAL_MISSING =100;
    public static final Integer ERROR_GENERAL_INVALID = 101;
    public static final Integer ERROR_GENERAL_NOT_FOUND = 102;
    public static final Integer ERROR_GENERAL_CUSTOM = 103;

    public static final ServiceError getServiceError(Integer errorCode,String message) {
        switch (errorCode) {
            case 100:
                return new ServiceError(errorCode,String.format("Required parameter missing for:%s",message));

            case 101:
                return new ServiceError(errorCode,String.format("Invalid value for:%s",message));
            case 102:
                return new ServiceError(errorCode,String.format("Data not found for:%s",message));
            default:
                return new ServiceError(errorCode,message);
        }
    }
}
