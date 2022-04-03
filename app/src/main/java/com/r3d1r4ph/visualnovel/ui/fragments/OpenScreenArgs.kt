package com.r3d1r4ph.visualnovel.ui.fragments

import com.r3d1r4ph.visualnovel.domain.ScreenTypeEnum

data class OpenScreenArgs(
    val screenId: Int,
    val name: String?,
    val screenType: ScreenTypeEnum
)
