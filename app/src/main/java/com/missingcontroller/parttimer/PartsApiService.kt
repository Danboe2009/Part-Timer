package com.missingcontroller.parttimer

import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PartsApiService {
    @GET("parts")
    fun listParts(
        @Query("access_token") user: String?,
        @Query("filter[where][owner]") owner: String?
    ): Deferred<List<PartObject>>

    @POST("parts")
    fun addPart(
        @Query("access_token") user: String?,
        @Body part: AddPartObject
    ): Deferred<PartsResponse>
}

object PartsApi {
    val retrofitService: PartsApiService by lazy {
        getRetrofit().create(PartsApiService::class.java)
    }
}