package com.github.luecy1.nhkbangumi.entity.nowonair;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by you on 2018/01/08.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class NowOnAirRoot {

    NowOnAirList nowonair_list;

    public NowOnAirList getNowOnAirList() {
        return nowonair_list;
    }

    public void setNowOnAirList(NowOnAirList nowOnAirList) {
        this.nowonair_list = nowOnAirList;
    }
}
