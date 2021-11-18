package com.role.play.feature.ui.screen.createplace

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.role.play.R
import com.role.play.data.Person
import com.role.play.feature.app.App
import com.role.play.feature.ui.theme.Black900
import com.role.play.feature.ui.theme.Purple200
import com.role.play.feature.ui.theme.Teal200
import com.role.play.feature.ui.theme.oswald
import com.role.play.module.filter.getItemsClasses


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PersonsScreen(listClasses: List<String>, select: (listPerson: List<Person>)->Unit) {
    val listPersons = remember {
        mutableStateListOf<Person>()
    }
    select(listPersons)
    val scrollState = rememberLazyListState()
    var openDialog by remember { mutableStateOf(true) }
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(1f)){
        LazyColumn(Modifier.fillMaxWidth(), scrollState, PaddingValues(16.dp, 24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)){
            items(listPersons){
                ItemPerson( it)
            }
        }
        AnimatedVisibility(
            visible = !scrollState.isScrollInProgress, Modifier.align(Alignment.BottomCenter),
            fadeIn(), fadeOut()
        ) {
            FloatingActionButton(onClick = {
                openDialog = true
            }, Modifier.border(2.dp , Teal200  , CircleShape) ,
                contentColor = Teal200, backgroundColor =  MaterialTheme.colors.background
            ) {
                Icon(Icons.Default.Add, null )
            }
        }
    }
    if (openDialog){
        DialogCreatePerson(listClasses, close = { openDialog = false }){
            listPersons.add(it)
            openDialog = false
        }
    }
}


@Composable
fun DialogCreatePerson(listClasses: List<String>, close: ()-> Unit ,onClickCreate: (person: Person)->Unit) {
    var name by remember { mutableStateOf("") }
    var selectedClass: String? by remember { mutableStateOf(null) }
    var item : String? by remember { mutableStateOf(null) }


    Dialog(onDismissRequest = {}) {
        Column(
            Modifier
                .background(Black900, MaterialTheme.shapes.medium)
                .padding(16.dp),verticalArrangement = Arrangement.SpaceBetween) {
            Text(stringResource(R.string.create_person) , fontFamily = oswald ,
                fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = Modifier.padding(bottom = 16.dp))
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(stringResource(R.string.name)) },
                singleLine = true
            )
            Spacer(Modifier.height(8.dp))
            var visibleClasses by remember {
                mutableStateOf(false)
            }
            val rotationStateClasses by animateFloatAsState(
                if(visibleClasses) 180f else 0f
            )
            Column {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                        .clickable { visibleClasses = !visibleClasses }, horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text(selectedClass?: stringResource(R.string.сlasses), Modifier,
                        fontSize = 20.sp)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null , Modifier.rotate(rotationStateClasses))
                }
                DropdownMenu(expanded = visibleClasses, onDismissRequest = { visibleClasses =false }) {
                    listClasses.forEach {
                        Text(it,
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 4.dp,)
                                .clickable {
                                    selectedClass = it
                                    item = null
                                    visibleClasses = false
                                },
                            fontSize = 20.sp
                        )
                    }
                }
            }
            var visible by remember {
                mutableStateOf(false)
            }
            val rotationState by animateFloatAsState(
                if(visible) 180f else 0f
            )
            Column {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .clickable { if(selectedClass!=null) visible = !visible },
                    horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text(item?: stringResource(R.string.item), Modifier,
                        fontSize = 20.sp)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null , Modifier.rotate(rotationState))
                }
                DropdownMenu(expanded = visible, onDismissRequest = { visible =false }) {
                    selectedClass?.let {
                        getItemsClasses(it).forEach { nameItem->
                            Text(nameItem,
                                Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 4.dp,)
                                    .clickable {
                                        item = nameItem
                                        visible = false
                                    }, fontSize = 20.sp)
                        }
                    }
                }
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                TextButton(onClick = close) {
                    Text(stringResource(R.string.close), fontFamily = oswald , fontWeight = FontWeight.Bold)
                }
                TextButton(onClick = {
                    if (selectedClass !=null && item!=null && name.isNotEmpty()){
                        onClickCreate(
                            Person(name , selectedClass!!, item!!)
                        )
                    }else{
                        Toast.makeText(
                            App.appContext,
                            "Заполните все недостоющие значения",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }) {
                    Text(stringResource(R.string.save), fontFamily = oswald , fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

//TODO добавить анимации
@Composable
fun ItemPerson(person: Person) {
    Column(
        Modifier
            .shadow(5.dp)
            .fillMaxWidth()
            .background(Black900, MaterialTheme.shapes.small)
            .border(1.dp, Purple200, MaterialTheme.shapes.small)
            .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally , verticalArrangement = Arrangement.Center) {

        Text(person.name , fontFamily = oswald , fontWeight = FontWeight.Bold)
        Text(person.classPerson +" - "+ person.item, fontFamily = oswald , fontWeight = FontWeight.Light)

    }
}

