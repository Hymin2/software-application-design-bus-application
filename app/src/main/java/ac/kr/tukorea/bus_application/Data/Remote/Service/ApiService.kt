package ac.kr.tukorea.bus_application.Data.Remote.Service

import ac.kr.tukorea.bus_application.BuildConfig
import ac.kr.tukorea.bus_application.Data.Remote.DTO.RouteDetailsStopDTO
import ac.kr.tukorea.bus_application.Data.Remote.DTO.SearchRouteDTO
import ac.kr.tukorea.bus_application.Data.Remote.DTO.SearchStopDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
   @GET (BuildConfig.ENDPOINT_GET_STOP_LIST)
   fun getStopList(
      @Query("name")
      name : String
   ) : Call<ArrayList<SearchStopDTO>>

   @GET (BuildConfig.ENDPOINT_GET_ROUTE_LIST)
   fun getRouteList(
      @Query("name")
      name : String
   ) : Call<ArrayList<SearchRouteDTO>>

   @GET(BuildConfig.ENDPOINT_GET_ROUTE_DETAILS_LIST)
   fun getRouteDetailsList(
      @Query("route_id")
      route_id : Int
   ) : Call<ArrayList<RouteDetailsStopDTO>>

   @GET(BuildConfig.ENDPOINT_GET_STOP_BY_ID_LIST)
   fun getStopById(
      @Query("id")
      id : Int
   ) : Call<SearchStopDTO>
}