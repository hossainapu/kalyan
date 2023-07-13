package com.example.polls.payload;

import com.example.polls.util.ServiceError;
import com.example.polls.util.ServiceResponse;

import java.io.Serializable;

public class ApplicationEnrollResponse extends ServiceResponse implements Serializable {

    private Integer applicationId;
    private String referenceNumber;

    public ApplicationEnrollResponse() {
        super();
    }

    public ApplicationEnrollResponse(ServiceError error) {
        super(error);
    }

    public ApplicationEnrollResponse(Integer applicationId,String referenceNumber) {
        super();
        this.applicationId = applicationId;
        this.referenceNumber = referenceNumber;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
}
