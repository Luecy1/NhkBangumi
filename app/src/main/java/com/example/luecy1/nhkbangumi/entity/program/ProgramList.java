package com.example.luecy1.nhkbangumi.entity.program;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ProgramList {

    private List list;

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
