package com.example.bus_application.DTO

import com.google.gson.annotations.SerializedName

data class SearchBusDTO(
    @SerializedName("routeno")
    val bus_number : Int,
    @SerializedName("")
    val bus_region : String,
    val start_stop : String,
    val end_stop : String,
    val start_time : String,
    val end_time : String
)
