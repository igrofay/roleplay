package com.role.play.feature.ui.screen.createplace

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.role.play.data.Person

class ViewModelCreatePlace : ViewModel() {
    var setting : String? = null
    var room : String? = null
    var listPerson = listOf<Person>()
}