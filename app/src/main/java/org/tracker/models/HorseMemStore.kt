package org.tracker.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class HorseMemStore : HorseStore, AnkoLogger {

    private val horses = ArrayList<HorseModel>()

    override fun findAll(): List<HorseModel> {
        return horses
    }

    override fun create(horse: HorseModel) {
        horse.id = getId()
        horses.add(horse)
        logAll()
    }
 //updating the horse details
    override fun update(horse: HorseModel) {
        var foundHorse: HorseModel? = horses.find { p -> p.id == horse.id }
        if (foundHorse != null) {
            foundHorse.title = horse.title
            foundHorse.owner = horse.owner
            foundHorse.breeder=horse.breeder
            foundHorse.sex = horse.sex
            foundHorse.image = horse.image
            foundHorse.trainer = horse.trainer
            foundHorse.lat = horse.lat
            foundHorse.lng = horse.lng
            foundHorse.zoom = horse.zoom
            logAll()
        }
    }
    override fun delete(horse: HorseModel) {
        horses.remove(horse)
    }

     fun logAll() {
        horses.forEach{ info("${it}") }
    }
}