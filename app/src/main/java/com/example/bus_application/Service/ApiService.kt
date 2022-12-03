package com.example.bus_application.Service

import com.example.bus_application.BuildConfig
import com.example.bus_application.DTO.SearchStopDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

   @GET ("stop")
   fun getStopList(
      @Query("name")
      name : String
   ) : Call<MutableList<SearchStopDTO>>
}