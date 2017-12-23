package com.example.luecy1.nhkbangumi.entity.description;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DescriptionList {

    private List list;

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
