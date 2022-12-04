package ac.kr.tukorea.bus_application.Data.Remote.DTO

import com.google.gson.annotations.SerializedName

data class RouteDetailsStopDTO(
    @SerializedName("stop_id")
    val stop_id : Int,
    @SerializedName("stop_name")
    val stop_name : String,
    @SerializedName("stop_order")
    val stop_order : Int,
    @SerializedName("updown")
    val updown : String,
    @SerializedName("mobile_no")
    val mobile_no : String
)
