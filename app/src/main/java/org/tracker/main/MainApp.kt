package org.tracker.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.tracker.models.HorseJSONStore
import org.tracker.models.HorseMemStore
import org.tracker.models.HorseStore

class MainApp : Application(), AnkoLogger {

    //val horses = ArrayList<HorseModel>() //putting horse collection into this class
    lateinit var horses: HorseStore

    override fun onCreate() {
        super.onCreate()
        horses = HorseMemStore()
        horses = HorseJSONStore(applicationContext)
        info("Horse started")
    }
}