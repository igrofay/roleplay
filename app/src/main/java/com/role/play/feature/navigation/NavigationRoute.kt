package com.role.play.feature.navigation

sealed class NavigationRoute(val route:String){
    object StartRoute : NavigationRoute("start_route")
    object ListPlacesRoute : NavigationRoute("place_route")
    object CreatePlaceRoure : NavigationRoute("create_place_route"){
        object SettingRoute : NavigationRoute("setting_route")
        object RoomRoute : NavigationRoute("room_route")
        object PersonsRoute : NavigationRoute("persons_route")
    }
    object  DescriptionPlaceRoute : NavigationRoute(
        "description_place_route"
    ){
        val routeAll: String
            get() = "$route/{$index}"
        val index = "index"
        fun routeArg(index:Int) = "$route/$index"
    }
}
