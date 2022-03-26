package com.r3d1r4ph.visualnovel

interface ScreenDataSource {
    fun getScreenById(id: Int): ResultWrapper<Screen>
}