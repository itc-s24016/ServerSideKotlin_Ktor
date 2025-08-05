package jp.ac.it_college.std.s24016.kotlin.ktor.examole.model

import kotlinx.serialization.Serializable

@Serializable
data class UserSession(
    // varでも問題ない
    val id: Long,
    val email: String
)
