package com.github.luecy1.nhkbangumi.service;

import com.github.luecy1.nhkbangumi.entity.description.DescriptionListRoot;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by you on 2018/01/27.
 */

public interface NhkService {

    @GET("v2/pg/info/{area}/{service}/{id}.json")
    Call<List<DescriptionListRoot>> programInfo(
            @Path("area") String area,
            @Path("service") String service,
            @Path("id") String id,
            @Query("apikey") String apikey
    );
}
