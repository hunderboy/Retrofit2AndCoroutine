package kr.co.everex.retrofit2andcoroutine.`interface`

import kotlinx.coroutines.Deferred
import kr.co.everex.retrofit2andcoroutine.dataclass.LatestNews
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiInterface {
    //fetches latest news with the required query params
    @GET("v2/everything")
    fun fetchLatestNewsAsync(
        @Query("q") query: String,
        @Query("sortBy") sortBy : String
    ) : Deferred<Response<LatestNews>>


}