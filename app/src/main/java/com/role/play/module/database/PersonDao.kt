package com.role.play.module.database

import androidx.compose.ui.layout.LayoutIdParentData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.role.play.data.Person
import com.role.play.data.Place

@Dao
interface PersonDao {
    @Query("SELECT * FROM t_person WHERE idParent IN (:idParent)")
    fun getAll(idParent: Int) : List<Person>
    @Insert
    fun insert(places: List<Person>)
    @Delete
    fun delete(places: List<Person>)
}