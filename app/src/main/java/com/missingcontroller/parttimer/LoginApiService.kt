package com.missingcontroller.parttimer

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface LoginApiService {
    @GET("User/login")
    fun logIn(@Body user: UserAccount): Call<LoginResponse>
}
