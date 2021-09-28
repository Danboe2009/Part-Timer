package com.missingcontroller.parttimer

import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface PartsApiService {
    @GET("parts")
    fun listParts(@Query("access_token") user: String?): Deferred<List<PartObject>>
}

object PartsApi {
    val retrofitService: PartsApiService by lazy {
        getRetrofit().create(PartsApiService::class.java)
    }
}