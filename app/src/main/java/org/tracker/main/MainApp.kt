package org.tracker.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.tracker.models.HorseMemStore
import org.tracker.models.HorseModel

class MainApp : Application(), AnkoLogger {

    //val horses = ArrayList<HorseModel>() //putting horse collection into this class
    val horses = HorseMemStore()
}