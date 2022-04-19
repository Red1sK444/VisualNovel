package com.r3d1r4ph.visualnovel.data.utils

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import timber.log.Timber

class Utils {
    companion object {
        inline fun <reified T> parseJsonToClassByType(jsonFileString: String): T {
           // Timber.i(jsonFileString)
            return Json.decodeFromString(jsonFileString)
        }
    }
}