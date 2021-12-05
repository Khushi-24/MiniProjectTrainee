package com.example.MiniProjectApis.Common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListResponse {

    private Object list;

    public Object getList() {
        return list;
    }

    public ListResponse(Object list) {
        this.list = list;
    }
}
