package com.missingcontroller.parttimer

import com.missingcontroller.parttimer.create.CreateAccount
import com.missingcontroller.parttimer.create.CreateAccountResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {
    @POST("Users/login")
    fun submitLogIn(@Body user: UserAccount): Deferred<LoginResponse>

    @POST("Users")
    fun submitCreateAccount(@Body user: CreateAccount): Deferred<CreateAccountResponse>
}

object LoginApi {
    val retrofitService: LoginApiService by lazy {
        getRetrofit().create(LoginApiService::class.java)
    }
}