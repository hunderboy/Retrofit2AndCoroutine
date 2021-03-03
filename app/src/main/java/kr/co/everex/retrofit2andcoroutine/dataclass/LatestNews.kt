package kr.co.everex.retrofit2andcoroutine.dataclass

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kr.co.everex.retrofit2andcoroutine.dataclass.Article

data class LatestNews (

    @SerializedName("status")
    @Expose
    val status: String,
    @SerializedName("totalResults")
    @Expose
    val totalResults: Int,
    @SerializedName("articles")
    @Expose
    val articles: List<Article>

)