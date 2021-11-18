package com.role.play.module.filter

val itemsHuman = listOf("Щит","Нож","Клинок Аракана")

val itemsElf = listOf("Лук","Меч","Кинжалы")

val itemsMagician = listOf("Посох","Артефакт","Волшебная шляпа")

val itemsDwarf = listOf("Кирка","Каска","Молот")

val itemsOrg = listOf("Меч","Копьё","Дубина")


val itemsAndroid = listOf("Контроль сети","Упраление роботами","Имитация человека")

val itemsHacker = listOf("Катана", "Робот-питомец","Суперкомпьютер")

val itemsCorporate = listOf("Имплант","Штурмовая винтовка","Корпорация")

val itemsChildStreet = listOf("Банда","Пистолеты-пулемёты","Чёрный рынок")

val itemsNomad = listOf("Машина","Семья","Винтовка")

fun getItemsClasses(classPerson: String) = when(classPerson){
    classesFantasy[0]-> itemsOrg
    classesFantasy[1]->itemsDwarf
    classesFantasy[2]->itemsMagician
    classesFantasy[3]->itemsElf
    classesFantasy[4]-> itemsHuman
    classesCyberpunk[0]-> itemsAndroid
    classesCyberpunk[1]->itemsHacker
    classesCyberpunk[2]->itemsChildStreet
    classesCyberpunk[3]->itemsNomad
    classesCyberpunk[4]-> itemsCorporate
    else-> listOf()
}