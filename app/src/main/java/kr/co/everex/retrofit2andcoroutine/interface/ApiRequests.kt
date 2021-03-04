package kr.co.everex.retrofit2andcoroutine.`interface`

import kr.co.everex.retrofit2andcoroutine.dataclass.RandomCatFacts
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST


/**
 * cat-facts 관련
 */
interface ApiRequests {

    @GET("/facts/random")
    fun getCatFacts(): Call<RandomCatFacts>

    /**
     * 아래 부터는 MyServer 에서 사용
     */
    @POST("/join/insert")
    fun insertUserData(): Call<RandomCatFacts>
}