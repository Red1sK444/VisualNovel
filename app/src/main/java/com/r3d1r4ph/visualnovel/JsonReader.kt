package com.r3d1r4ph.visualnovel

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class JsonReader {

    companion object {
        fun getJsonDataFromAsset(context: Context, fileName: String): String? {
            val jsonString: String
            try {
                jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return null
            }
            parseJson(jsonString)
            return jsonString
        }

        private fun parseJson(jsonFileString: String) {
            Log.i("dataaa", jsonFileString)
            val gson = Gson()
            val listScreenType = object : TypeToken<List<Screen>>() {}.type
            val screens: List<Screen> = gson.fromJson(jsonFileString, listScreenType)
            screens.forEachIndexed { index, screen -> Log.i("dataaa", "> Item $index:\n$screen") }
        }
    }
}