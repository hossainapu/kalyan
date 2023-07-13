package com.example.polls.payload;

import java.io.Serializable;

public class SearchApplicationRequest implements Serializable {

    private OnlineApplicationSearchCriteria criteria;

    public OnlineApplicationSearchCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(OnlineApplicationSearchCriteria criteria) {
        this.criteria = criteria;
    }
}
