package ac.kr.tukorea.bus_application.Client


import ac.kr.tukorea.bus_application.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var instance : Retrofit? = null

    open fun getApiInstance() : Retrofit{
        if(instance == null){
            instance = Retrofit.Builder()
                .baseUrl(BuildConfig.URL_BUS)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return instance!!
    }
}