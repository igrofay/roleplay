package com.role.play.feature.ui.screen.listplaces

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.role.play.R
import com.role.play.data.Place
import com.role.play.feature.ui.theme.*
import com.role.play.module.database.WorkWithDatabase
import com.role.play.module.filter.settingList
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ListPlacesScreen(listPlaces : SnapshotStateList<Place>, onClickOpenPlace: (index: Int) -> Unit, onClickCreatePlace: ()-> Unit  ){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClickCreatePlace, Modifier.border(1.dp , Purple200 , CircleShape) ,
                contentColor = Purple200, backgroundColor =  colors.background
            ) {
                Icon(Icons.Default.Add, null )
            }
        }
    ) {
        if (listPlaces.isEmpty()){
            NullPlaces()
        }else{
            ListPlaces(listPlaces, onClickOpenPlace)
        }

    }
}

@Composable
fun NullPlaces() {
    Column(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxWidth()
                .weight(1f), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
            Text(
                stringResource(R.string.message_null), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold,
                color = Purple500 , fontSize = 55.sp , lineHeight = 60.sp , fontFamily = oswald,
            )
            Text(
                stringResource(R.string.null_places), textAlign = TextAlign.Center,
                fontFamily = oswald, fontWeight = FontWeight.Light , color = Purple200
            )
        }

        GlideImage(imageModel = R.drawable.dragon,
            Modifier
                .fillMaxWidth()
                .weight(1.5f) , colorFilter = ColorFilter.tint(Color.White), alignment = Alignment.BottomCenter)

    }
}

@Composable
fun ListPlaces(listPlaces : SnapshotStateList<Place>, onClick: (index: Int) -> Unit) {
    var visible by remember {
        mutableStateOf(false)
    }
    var indexDelay by remember {
        mutableStateOf(-1)
    }

    LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(32.dp, 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)){
        itemsIndexed(listPlaces){ index , place ->
            ItemPlaces(place,{
                onClick(index)
            }){
                visible = true
                indexDelay = index
            }
        }
    }
    val scope= rememberCoroutineScope()
    if (visible)
        DelayPlace({
            visible = false
        }){
            visible = false
            scope.launch(Dispatchers.IO){
                WorkWithDatabase.delay(
                    listPlaces.removeAt(indexDelay)
                )
            }
        }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemPlaces(place: Place, onClickOpen: () -> Unit, onClickDelay : ()->Unit ) {
    Card(
        Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(8.dp),
        RoundedCornerShape(12.dp),
        backgroundColor = if (settingList[0]== place.setting) Purple200 else Teal200,
        elevation = 6.dp
    ){
        Column(
            Modifier
                .fillMaxSize()
                .combinedClickable(onClick = onClickOpen, onLongClick = onClickDelay),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("${place.setting}\n${stringResource(R.string.location)}: ${place.room}", color = Color.White,
                fontFamily = oswald , fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, style = typography.subtitle1)
        }

    }
}

@Composable
fun DelayPlace(onClickDismiss: () -> Unit, onClickDelay: () -> Unit) {
    AlertDialog(
        onDismissRequest = onClickDismiss,
        title = {},
        text = {Text(stringResource(R.string.delay), fontFamily = oswald, fontSize = 26.sp,
            fontWeight = FontWeight.Bold, color = Color.White)},
        buttons = {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                TextButton(onClick = onClickDismiss) {
                    Text(stringResource(R.string.close), )
                }
                TextButton(onClick = onClickDelay) {
                    Text(stringResource(R.string.delay))
                }
            }
        }
        )
}