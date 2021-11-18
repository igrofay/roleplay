package com.role.play.data

data class Place(
    val setting:  String,
    val room: String,
    val persons: List<Person>,
    var date : String? = "",
    val descriptionRoom: String = ""
)
