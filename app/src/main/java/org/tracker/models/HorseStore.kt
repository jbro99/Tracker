package org.tracker.models

interface HorseStore {
    fun findAll(): List<HorseModel>
    fun create(horse: HorseModel)
    fun update(horse: HorseModel)
    fun delete(horse: HorseModel)
}
