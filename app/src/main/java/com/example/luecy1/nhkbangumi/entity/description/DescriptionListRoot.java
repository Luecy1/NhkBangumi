package com.example.luecy1.nhkbangumi.entity.description;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DescriptionListRoot {

    private DescriptionList list;

    public DescriptionList getList() {
        return list;
    }

    public void setList(DescriptionList list) {
        this.list = list;
    }
}
