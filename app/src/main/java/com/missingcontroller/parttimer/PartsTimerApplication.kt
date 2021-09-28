package com.missingcontroller.parttimer

import android.app.Application
import android.content.Context

class PartsTimerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        appInstance = this
    }

    companion object {
        var appInstance:PartsTimerApplication? = null
        var mainActivity:MainActivity?=null
        var context: Context? = null
    }
}