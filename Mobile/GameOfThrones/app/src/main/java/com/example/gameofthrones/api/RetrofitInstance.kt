package com.example.gameofthrones.api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.gameofthrones.util.Constants.Companion.BASE_URL
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitInstance {

    companion object {
        var mRetrofit: Retrofit? = null

        fun getInstance(): Retrofit{
            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                // we need to add converter factory to
                // convert JSON object to Java object
                .build()

    }
}}
