package com.r3d1r4ph.visualnovel.data.dto

import com.r3d1r4ph.visualnovel.domain.models.Screen
import com.r3d1r4ph.visualnovel.domain.models.ScreenTypeEnum
import kotlinx.serialization.Serializable

@Serializable
data class ScreenDto(
    val id: Int,
    val message: String,
    val screenType: ScreenTypeEnum,
    val background: String,
    val actions: List<ActionDto>
) {
    fun toDomain() =
        Screen(
            id = id,
            message = message,
            screenType = screenType,
            background = background,
            actions = actions.map { it.toDomain() }
        )
}
