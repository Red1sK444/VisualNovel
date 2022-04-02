package com.r3d1r4ph.visualnovel.common

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import timber.log.Timber
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

        inline fun <reified T> parseJsonToObjectByType(jsonFileString: String): T {
            Timber.i(jsonFileString)

            val objectTypeToken = object : TypeToken<T>() {}.type
            return Gson().fromJson(jsonFileString, objectTypeToken)
        }
    }
}
