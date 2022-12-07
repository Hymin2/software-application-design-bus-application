package ac.kr.tukorea.bus_application.Data.Remote.DTO

data class AllBusDTO(
    val route_id : Int,
    val bus_id : String,
    val gps_x : Double,
    val gps_y : Double,
    val current_stop : Int
)
