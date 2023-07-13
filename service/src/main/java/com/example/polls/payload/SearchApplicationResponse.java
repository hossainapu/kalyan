package com.example.polls.payload;

import com.example.polls.util.ServiceError;
import com.example.polls.util.ServiceResponse;

import java.io.Serializable;
import java.util.List;

public class SearchApplicationResponse extends ServiceResponse implements Serializable {

    private List<OnlineApplicationInfo> results;

    public SearchApplicationResponse() {
        super();
    }

    public SearchApplicationResponse(ServiceError error) {
        super(error);
    }

    public SearchApplicationResponse(List<OnlineApplicationInfo> results) {
        super();
        this.results = results;
    }

    public List<OnlineApplicationInfo> getResults() {
        return results;
    }

    public void setResults(List<OnlineApplicationInfo> results) {
        this.results = results;
    }
}
