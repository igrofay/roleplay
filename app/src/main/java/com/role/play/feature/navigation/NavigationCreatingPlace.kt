package com.role.play.feature.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.role.play.feature.ui.screen.createplace.PersonsScreen
import com.role.play.feature.ui.screen.createplace.RoomScreen
import com.role.play.feature.ui.screen.createplace.SettingScreen
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.role.play.feature.ui.screen.createplace.ViewModelCreatePlace
import com.role.play.module.filter.getClassesPerson
import com.role.play.module.filter.getRooms

@Composable
fun NavigationCreatePlace(nav: NavHostController, modifier: Modifier, model: ViewModelCreatePlace, setLevel: (level: Int)-> Unit) {

    NavHost(nav, NavigationRoute.CreatePlaceRoure.SettingRoute.route, modifier){
        composable(NavigationRoute.CreatePlaceRoure.SettingRoute.route){
            setLevel(1)
            SettingScreen{
                model.setting = it
            }
        }
        composable(NavigationRoute.CreatePlaceRoure.RoomRoute.route){
            setLevel(2)
            RoomScreen(getRooms(model.setting.toString())) {
                model.room = it
            }
        }
        composable(NavigationRoute.CreatePlaceRoure.PersonsRoute.route){
            setLevel(3)
            PersonsScreen(getClassesPerson(model.setting.toString())){
                model.listPerson= it
            }
        }
    }
}