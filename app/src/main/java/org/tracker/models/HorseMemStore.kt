package org.tracker.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class HorseMemStore : HorseStore, AnkoLogger {

    val horses = ArrayList<HorseModel>()

    override fun findAll(): List<HorseModel> {
        return horses
    }

    override fun create(horse: HorseModel) {
        horse.id = getId()
        horses.add(horse)
        logAll()
    }

    override fun update(horse: HorseModel) {
        var foundHorse: HorseModel? = horses.find { p -> p.id == horse.id }
        if (foundHorse != null) {
            foundHorse.title = horse.title
            foundHorse.description = horse.description
            logAll()
        }
    }
     fun logAll() {
        horses.forEach{ info("${it}") }
    }
}