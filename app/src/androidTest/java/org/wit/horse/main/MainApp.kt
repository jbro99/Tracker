package org.wit.horse.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.horse.models.HorseModel

class MainApp : Application(), AnkoLogger {

    val horses = ArrayList<HorseModel>()

    override fun onCreate() {
        super.onCreate()
        info("Horse Tracker started")
    }
}