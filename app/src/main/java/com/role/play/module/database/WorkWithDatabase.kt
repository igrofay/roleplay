package com.role.play.module.database

import androidx.room.Room
import com.role.play.data.Place
import com.role.play.feature.app.App

object WorkWithDatabase {
    private val db by lazy {
        Room.databaseBuilder(
            App.appContext,
            AppDatabase::class.java, App.tagDatabase
        ).build()
    }
    private val placeDao by lazy {
        db.placeDao()
    }
    private val personDao by lazy {
        db.personDao()
    }
    fun insertPlace(place: Place, idPlace : Int){
        place.id = idPlace
        place.persons.forEachIndexed{ index , it ->
            it.id = index
            it.idParent = idPlace
        }
        placeDao.insert(place)
        personDao.insert(place.persons)
    }
    fun getAll(): List<Place> {
        val places = placeDao.getAll()
        places.forEach {
            it.persons = personDao.getAll(it.id)
        }
        return places
    }
    fun delay(place: Place){
        placeDao.delete(place)
        personDao.delete(place.persons)
    }
}