package com.role.play.feature.ui.screen.createplace

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.role.play.R
import com.role.play.feature.ui.theme.oswald
import com.role.play.feature.ui.widgets.DropDownSelect

@Composable
fun RoomScreen(listRoom: List<String>,select : (value : String?)->Unit) {
    Column{
        Text(
            stringResource(R.string.choose_room) ,
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp) ,
            textAlign =  TextAlign.Start, fontFamily = oswald , fontWeight = FontWeight.Light)
        DropDownSelect(
            listRoom , select
        )
        Image(painterResource(R.drawable.blin), contentDescription = null , Modifier.padding(16.dp).fillMaxSize())
    }
}