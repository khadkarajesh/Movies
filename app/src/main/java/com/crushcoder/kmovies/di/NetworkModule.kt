package com.crushcoder.kmovies.di

import android.content.Context
import android.content.SharedPreferences
import com.crushcoder.kmovies.App
import com.crushcoder.kmovies.rest.retrofit.ApiResponseConverter
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {
    single { getInterceptor() }
    single { getGson() }
}

fun <T> create(t: Class<T>): T {
    val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.themoviedb.org/3/")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(ApiResponseConverter(Gson()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    return retrofit.create(t)
}

fun getInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

val dataModule = module {
    single { getSharedPref() }
}

fun getSharedPref(): SharedPreferences {
    return context.getSharedPreferences("AppPref", Context.MODE_PRIVATE)
}

fun getGson(): Gson {
    return Gson()
}

val context = App.get()