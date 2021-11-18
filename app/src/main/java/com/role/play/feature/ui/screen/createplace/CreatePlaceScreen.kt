package com.role.play.feature.ui.screen.createplace

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.role.play.R
import com.role.play.data.Person
import com.role.play.data.Place
import com.role.play.feature.app.App
import com.role.play.feature.navigation.NavigationCreatePlace
import com.role.play.feature.navigation.NavigationRoute
import com.role.play.feature.ui.theme.Purple500
import com.role.play.feature.ui.theme.oswald

@Composable
fun CreatePlaceScreen(model: ViewModelCreatePlace = viewModel(), onClick: (place:Place)->Unit ) {
    var level by remember {
        mutableStateOf(1)
    }
    val nav = rememberNavController()
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            Modifier
                .fillMaxWidth()
                .weight(0.5f), Alignment.BottomCenter) {
            Text(
                "LEVEL $level", textAlign = TextAlign.Center, fontWeight = FontWeight.Bold,
                color = Purple500 , fontSize = 55.sp , lineHeight = 60.sp , fontFamily = oswald,
            )
        }
        NavigationCreatePlace(nav ,
            Modifier
                .weight(2f)
                .fillMaxWidth(0.8f), model){
            level = it
        }
        Box(
            Modifier
                .fillMaxWidth()
                .weight(0.4f), Alignment.Center){
            Button(onClick = {
                             if (isInput(level, model)){
                                 next(level)?.let {
                                     nav.navigate(it)
                                 } ?: onClick(
                                     Place(model.setting!! , model.room!!, model.listPerson, "")
                                 )
                             }else{
                                 Toast.makeText(App.appContext, "Выберите значение", Toast.LENGTH_SHORT).show()
                             }
            }
                , Modifier.fillMaxWidth(0.4f) ) {
                Text(stringResource(R.string.next), Modifier.padding(bottom = 8.dp ),
                    fontFamily = oswald, fontWeight = FontWeight.Light , color = Color.Black , fontSize = 16.sp
                )
            }
        }
    }
}

fun next(level: Int) = when(level){
    1 -> NavigationRoute.CreatePlaceRoure.RoomRoute.route
    2 -> NavigationRoute.CreatePlaceRoure.PersonsRoute.route
    else -> null
}

fun isInput(level: Int, model: ViewModelCreatePlace) = when(level){
    1-> model.setting
    2-> model.room
    else-> ""
} != null