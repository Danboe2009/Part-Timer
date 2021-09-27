package com.missingcontroller.parttimer

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface PartsURLService {
    @GET("api/parts")
    fun listParts(@Query("access_token") user: String?): Call<List<PartObject>>
}

