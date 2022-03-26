package com.r3d1r4ph.visualnovel

data class Screen(
    val id: Int,
    val message: String,
    val screenType: ScreenTypes,
    val background: String,
    val actions: List<Action>
)
