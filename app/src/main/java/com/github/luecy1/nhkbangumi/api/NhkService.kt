package com.github.luecy1.nhkbangumi.api

import com.github.luecy1.nhkbangumi.entity.program.ProgramListRoot
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NhkService {

    @GET("/v2/pg/list/{area}/{service}/{date}.json")
    fun programList(
            @Path("area") area :String,
            @Path("service") service :String,
            @Path("date") date :String,
            @Query("key") apikey  : String): Call<ProgramListRoot>

}