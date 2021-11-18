package com.role.play.feature.main

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.role.play.data.Place
import com.role.play.module.database.WorkWithDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModelMain : ViewModel(){
    val listPlaces = mutableStateListOf<Place>()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            val list = WorkWithDatabase.getAll()
            withContext(Dispatchers.Main){
                listPlaces.addAll(list)
            }
        }
    }
}