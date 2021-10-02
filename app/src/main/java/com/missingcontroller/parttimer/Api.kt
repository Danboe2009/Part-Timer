package com.missingcontroller.parttimer

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .add(CustomDateAdapter())
    .build()


val client = OkHttpClient().newBuilder()
    //.addInterceptor(HttpLoggingInterceptor().apply{
    //level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    //})
    .addInterceptor { chain ->
        val request = chain.request()
        val response = chain.proceed(request)
        when (response.code) {
            401 -> {
                println("Bad Token")
                logout()
            }
            422 -> {

            }
        }
        response
    }
    .connectTimeout(60, TimeUnit.SECONDS)
    .writeTimeout(120, TimeUnit.SECONDS)
    .readTimeout(120, TimeUnit.SECONDS)
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl("https://part-timer-api.herokuapp.com/api/")
    .client(client)
    .build()

fun getRetrofit(): Retrofit {
    return retrofit
}

fun logout(){
    if (PartsTimerApplication.mainActivity != null) {
        PartsTimerApplication.mainActivity!!.logout()
    }
}