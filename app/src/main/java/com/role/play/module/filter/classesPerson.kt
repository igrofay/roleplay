package com.role.play.module.filter

val classesFantasy = listOf("Oрк", "Гном", "Маг", "Эльф","Человек")

val classesCyberpunk = listOf("Android" , "Хакер", "Дитя улиц","Кочевник","Корпорат")

fun getClassesPerson(nameSetting: String) = when(nameSetting){
    settingList[0] -> classesCyberpunk
    else -> classesFantasy
}