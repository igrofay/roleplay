package com.role.play.module.filter

val roomFantasy = listOf("Замок","Пещера","Лес","Таверна")

val roomCyberpunk = listOf("Пустоши", "Мегаполис","Бункер", "Атомная станция")

fun getRooms(nameSetting: String) = when(nameSetting){
    settingList[0] -> roomCyberpunk
    else -> roomFantasy
}