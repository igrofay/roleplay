package com.role.play.feature.navigation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.role.play.feature.main.ViewModelMain
import com.role.play.feature.ui.screen.createplace.CreatePlaceScreen
import com.role.play.feature.ui.screen.listplaces.ListPlacesScreen
import com.role.play.feature.ui.screen.start.StartScreen
import com.role.play.R
import com.role.play.data.Place
import com.role.play.feature.app.App
import com.role.play.feature.ui.screen.description.DescriptionPlaceScreen
import com.role.play.feature.ui.theme.Purple200
import com.role.play.feature.ui.theme.oswald
import com.role.play.module.database.WorkWithDatabase
import com.role.play.module.web.WorkWithWeb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun NavigationApp(model: ViewModelMain = viewModel()) {
    val navHost = rememberNavController()
    NavHost(navHost, NavigationRoute.StartRoute.route){
        composable( NavigationRoute.StartRoute.route){
            StartScreen(navHost)
        }
        composable( NavigationRoute.ListPlacesRoute.route){
            ListPlacesScreen(model.listPlaces, {
                navHost.navigate(
                    NavigationRoute.DescriptionPlaceRoute.routeArg(it)
                )
            }){
                navHost.navigate(NavigationRoute.CreatePlaceRoure.route)
            }
        }
        composable(NavigationRoute.CreatePlaceRoure.route){
            var visible by remember {
                mutableStateOf(false)
            }
            CreatePlaceScreen{
                visible = true
                WorkWithWeb.server.getDescription(it).enqueue(object : Callback<Place>{
                    override fun onResponse(call: Call<Place>, response: Response<Place>) {
                        response.body()?.let { place ->
                            place.id = model.listPlaces.size
                            model.viewModelScope.launch(Dispatchers.IO) {
                                WorkWithDatabase.insertPlace(
                                    place , model.listPlaces.size
                                )
                            }
                            model.listPlaces.add(place)
                            navHost.navigate(
                                NavigationRoute.DescriptionPlaceRoute.routeArg(model.listPlaces.size-1)
                            ){
                                popUpTo(NavigationRoute.CreatePlaceRoure.route){inclusive = true}
                            }
                        } ?: Toast.makeText(App.appContext, R.string.error_on_server, Toast.LENGTH_SHORT).show()
                        visible = false
                    }
                    override fun onFailure(call: Call<Place>, t: Throwable) {
                        visible = false
                        Toast.makeText(App.appContext, t.message, Toast.LENGTH_SHORT).show()
                    }

                })
            }
            if (visible) DialogLoading()
        }
        composable(
            NavigationRoute.DescriptionPlaceRoute.routeAll,
            arguments = listOf(
                navArgument(NavigationRoute.DescriptionPlaceRoute.index){ type = NavType.IntType }
            )
        ){backStackEntry->
            val index = backStackEntry.arguments?.getInt(NavigationRoute.DescriptionPlaceRoute.index)
            index?.let {
                DescriptionPlaceScreen(model.listPlaces[it])
            }
        }
    }
}

@Composable
fun DialogLoading() {
    Dialog(onDismissRequest = {}) {
        Column(
            Modifier
                .height(250.dp)
                .background(Color.White, RoundedCornerShape(12.dp))
                .border(1.dp, Purple200, RoundedCornerShape(12.dp))
        ) {
            Box(
                Modifier
                    .weight(1f)
                    .fillMaxWidth(), Alignment.BottomCenter){
                Text(stringResource(R.string.generate_place), color = Color.Black,
                    fontFamily = oswald , fontWeight = FontWeight.Light, fontSize = 20.sp)
            }
            Image(painterResource(R.drawable.world),  null ,
                Modifier
                    .weight(2f)
                    .fillMaxWidth()
            )
        }
    }
}