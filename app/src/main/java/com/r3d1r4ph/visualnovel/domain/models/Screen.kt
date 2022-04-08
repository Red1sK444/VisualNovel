package com.r3d1r4ph.visualnovel.domain.models

data class Screen(
    val id: Int,
    val message: String,
    val screenType: ScreenTypeEnum,
    val background: String,
    val actions: List<Action>
)
