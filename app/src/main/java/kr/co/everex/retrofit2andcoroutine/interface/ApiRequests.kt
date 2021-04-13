package kr.co.everex.retrofit2andcoroutine.`interface`

import kr.co.everex.retrofit2andcoroutine.dataclass.RandomCatFacts
import retrofit2.Call
import retrofit2.http.*


/**
 * cat-facts 관련
 */
interface ApiRequests {

    @GET("/facts/random")
    fun getCatFacts(): Call<RandomCatFacts>

    /**
     * POST 요청 전달 방식
     */
    // POST ver_1
    @POST("/production/authorization/membership") // 요청 path
    fun insertUserData(): Call<RandomCatFacts> // Call<"리턴값으로 전달받을 데이터 형태">

    // POST ver_2
    @FormUrlEncoded
    @POST("/test")
    fun postRequest(
            // 지정한 필드 삽입
            @Field("id") id: String,
            @Field("pw") pw: String
    ): Call<ResponseDTO>

    // POST ver_3
    @POST("/{path}")
    fun testRequest(
            // 요청 path 직접삽입
            @Path("path")path: String,
            @Body parameters: HashMap<String, Any>
    ): Call<ResponseDTO>

    // POST ver_4
    @POST("membership")
    fun testRequest2(
            @Body parameters: HashMap<String, Any>
    ): Call<ResponseDTO>

}
// 응답받을 response 데이터 형태
data class ResponseDTO(
    var response:Boolean = false,
    var msg:String? = null
)