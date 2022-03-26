package com.r3d1r4ph.visualnovel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.black)

        JsonController.getJsonDataFromAsset(this, "visual_novel.jsonc")
    }
}