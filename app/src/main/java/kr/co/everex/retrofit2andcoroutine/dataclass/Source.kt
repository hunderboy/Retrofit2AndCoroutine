package kr.co.everex.retrofit2andcoroutine.dataclass

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * cat-facts 관련
 */
data class Source (
    @SerializedName("id")
    @Expose
    val id: Any,

    @SerializedName("name")
    @Expose
    val name: String
)
