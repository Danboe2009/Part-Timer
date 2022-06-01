package com.missingcontroller.parttimer.part

import android.app.Application
import android.content.Context
import com.missingcontroller.parttimer.MainActivity

class PartsTimerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        appInstance = this
    }

    companion object {
        var appInstance: PartsTimerApplication? = null
        var mainActivity: MainActivity?=null
        var context: Context? = null
    }
}