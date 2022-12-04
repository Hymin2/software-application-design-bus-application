package ac.kr.tukorea.bus_application.Data.Remote.DTO

data class SearchStopList(
    var searchStopDTO: ArrayList<SearchStopDTO>,
    var checkedBox : ArrayList<Boolean>
)