package com.crushcoder.kmovies.di

import android.content.Context
import android.content.SharedPreferences
import com.crushcoder.kmovies.App
import com.crushcoder.kmovies.rest.retrofit.ApiResponseConverter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.JsonParseException
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


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
            .addConverterFactory(ApiResponseConverter(getDateDeserializer()))
            .addConverterFactory(GsonConverterFactory.create(getDateDeserializer()))
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

fun getDateDeserializer(): Gson {
    var dateFormats = listOf("yyyy-MM-dd'T'HH:mm:ssZ",
            "yyyy-MM-dd'T'HH:mm:ss",
            "yyyy-MM-dd",
            "EEE MMM dd HH:mm:ss z yyyy",
            "HH:mm:ss",
            "MM/dd/yyyy HH:mm:ss aaa",
            "yyyy-MM-dd'T'HH:mm:ss.SSSSSS",
            "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS",
            "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'",
            "MMM d',' yyyy H:mm:ss a")

    var gsonBuilder = GsonBuilder()
    gsonBuilder.registerTypeAdapter(Date::class.java, JsonDeserializer<Date> { json, _, _ ->
        for (format in dateFormats) {
            try {
                return@JsonDeserializer SimpleDateFormat(format, Locale.US).parse(json.asString)
            } catch (e: ParseException) {
            }
        }
        throw JsonParseException("Unparseable date: \"" + json.asString
                + "\". Supported formats: \n" + dateFormats.toString())
    })
    return gsonBuilder.create()
}

val context = App.get()