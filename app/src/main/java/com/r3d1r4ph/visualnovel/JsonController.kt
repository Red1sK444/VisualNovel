package com.r3d1r4ph.visualnovel

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class JsonController {

    companion object {
        fun getJsonDataFromAsset(context: Context, fileName: String): String? {
            val jsonString: String
            try {
                jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return null
            }
            return jsonString
        }

        fun <T> parseJsonToObjectByType(jsonFileString: String): T {
            Log.i("dataaa", jsonFileString)

            val objectTypeToken = object : TypeToken<T>() {}.type

            //screens.forEachIndexed { index, screen -> Log.i("dataaa", "> Item $index:\n$screen") }
            return Gson().fromJson(jsonFileString, objectTypeToken)
        }
    }
}
