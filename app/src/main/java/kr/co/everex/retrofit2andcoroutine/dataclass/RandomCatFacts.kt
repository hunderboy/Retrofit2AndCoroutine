package kr.co.everex.retrofit2andcoroutine.dataclass


/**
 * cat-facts 관련
 */
data class RandomCatFacts(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val deleted: Boolean,
    val source: String,
    val status: Status,
    val text: String,
    val type: String,
    val updatedAt: String,
    val used: Boolean,
    val user: String
)
