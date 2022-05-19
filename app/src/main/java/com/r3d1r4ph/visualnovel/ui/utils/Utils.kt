package com.r3d1r4ph.visualnovel.ui.utils

import android.content.Context
import java.io.IOException

object Utils {
    fun getJsonDataFromAsset(context: Context, fileName: String): String {
        return try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            ""
        }
    }
}