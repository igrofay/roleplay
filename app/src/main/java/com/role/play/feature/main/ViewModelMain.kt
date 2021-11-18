package com.role.play.feature.main

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.role.play.data.Person
import com.role.play.data.Place
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelMain : ViewModel(){
    val listPlaces = mutableStateListOf<Place>()
//    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            listPlaces.addAll(WorkWithDatabase.getAll())
//        }
//    }
}