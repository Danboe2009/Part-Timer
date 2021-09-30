package com.missingcontroller.parttimer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        PartsTimerApplication.mainActivity = this

        setContentView(R.layout.activity_main)
    }
}