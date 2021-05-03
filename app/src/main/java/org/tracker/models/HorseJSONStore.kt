package org.tracker.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.tracker.helpers.*
import java.util.*

val JSON_FILE = "horses.json" // new variable called json file and naming it horses.json. were going to serelize to this file
val gsonBuilder = GsonBuilder().setPrettyPrinting().create() //std way of setting up the gsonbuilder
val listType = object : TypeToken<java.util.ArrayList<HorseModel>>() {}.type //gson needs to know the list type thats going to be encoding and decoding

fun generateRandomId(): Long {
    return Random().nextLong()
}

class HorseJSONStore : HorseStore, AnkoLogger {

    val context: Context
    var horses = mutableListOf<HorseModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<HorseModel> {
        return horses
    }

    override fun create(horse: HorseModel) {
        horse.id = generateRandomId()
        horses.add(horse)
        serialize()
    }
    override fun delete(horse: HorseModel) {
        horses.remove(horse)
        serialize()
    }


    private fun serialize() {
        val jsonString = gsonBuilder.toJson(horses, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        horses = Gson().fromJson(jsonString, listType)
    }
    override fun update(horse: HorseModel) {
        val horsesList = findAll() as ArrayList<HorseModel>
        var foundHorse: HorseModel? = horsesList.find { p -> p.id == horse.id }
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
        }
        serialize()
    }
}