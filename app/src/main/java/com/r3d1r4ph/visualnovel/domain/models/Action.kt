package com.r3d1r4ph.visualnovel.domain.models

data class Action(
    val message: String,
    val toScreen: Int = -1
)