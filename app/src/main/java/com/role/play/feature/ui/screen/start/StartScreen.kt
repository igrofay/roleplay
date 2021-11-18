package com.role.play.feature.ui.screen.start

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.role.play.R
import com.role.play.feature.navigation.NavigationRoute
import com.role.play.feature.ui.theme.Purple200
import com.role.play.feature.ui.theme.Purple500
import com.role.play.feature.ui.theme.oswald
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.delay

@Composable
fun StartScreen(navHost: NavHostController) {
    Column(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxWidth(0.9f)
                .weight(1f), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.End){
            Text(stringResource(R.string.start_game), textAlign = TextAlign.Right, fontWeight = FontWeight.Bold,
                color = Purple500 , fontSize = 65.sp , lineHeight = 60.sp , fontFamily = oswald,
            )
            Text(stringResource(R.string.create_you_location), textAlign = TextAlign.Right,
                fontFamily = oswald, fontWeight = FontWeight.Light , color = Purple200
            )
        }
        var TRIGGER by remember {
            mutableStateOf(0)
        }
        val colors = listOf(Blue , Yellow, Red, White)
        val colorImage by animateColorAsState(
            colors[TRIGGER%colors.size],
            animationSpec = tween(
                durationMillis = 900)
        )

        LaunchedEffect(TRIGGER){
            delay(1000)
            TRIGGER++

        }
        Image(painterResource(R.drawable.ghost),
             null ,
            Modifier
                .fillMaxWidth()
                .weight(1.5f), colorFilter = ColorFilter.tint(colorImage))
        Box(
            Modifier
                .fillMaxWidth()
                .weight(0.4f), Alignment.TopCenter){

            Button(onClick = {
                navHost.navigate(NavigationRoute.ListPlacesRoute.route){
                    popUpTo(NavigationRoute.StartRoute.route){inclusive = true}
                }
            }, Modifier.fillMaxWidth(0.4f) ) {
                Text(stringResource(R.string.start), Modifier.padding(bottom = 8.dp ),
                    fontFamily = oswald, fontWeight = FontWeight.Light , color = Color.Black , fontSize = 16.sp
                )
            }
        }
    }
}