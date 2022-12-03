package com.example.bus_application

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var instance : Retrofit? = null

    open fun getBusInstance() : Retrofit {
        if(instance == null){
            instance = Retrofit.Builder()
                .baseUrl(BuildConfig.URL_BUS)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return instance!!
    }

    open fun getStopInstance() : Retrofit{
        if(instance == null){
            instance = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return instance!!
    }
}