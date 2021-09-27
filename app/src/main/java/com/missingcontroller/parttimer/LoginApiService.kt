package com.missingcontroller.parttimer

import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {
    @POST("user/login")
    fun submitLogIn(@Body user: UserAccount): Deferred<LoginResponse>
}

object LoginApi {
    val retrofitService: LoginApiService by lazy {
        getRetrofit().create(LoginApiService::class.java)
    }
}