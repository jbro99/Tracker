package org.tracker.models
//this interface contains the CRUD operation to be implemented in HorseMemStore and HorseJSONStore
interface HorseStore {
    fun findAll(): List<HorseModel>
    fun create(horse: HorseModel)
    fun update(horse: HorseModel)
    fun delete(horse: HorseModel)
}
