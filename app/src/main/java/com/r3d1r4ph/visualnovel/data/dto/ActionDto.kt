package com.r3d1r4ph.visualnovel.data.dto

import com.r3d1r4ph.visualnovel.domain.models.Action
import kotlinx.serialization.Serializable

@Serializable
data class ActionDto(
    val message: String,
    val toScreen: Int = -1
) {
    fun toDomain() =
        Action(
            message = message,
            toScreen = toScreen
        )
}
