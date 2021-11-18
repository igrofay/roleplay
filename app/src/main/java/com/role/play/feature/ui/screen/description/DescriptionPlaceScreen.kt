package com.role.play.feature.ui.screen.description

import com.role.play.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.role.play.data.Person
import com.role.play.data.Place
import com.role.play.feature.ui.theme.Purple700
import com.role.play.feature.ui.theme.oswald

@Composable
fun DescriptionPlaceScreen(place: Place) {
    val listState = rememberLazyListState()
    LazyColumn(Modifier.fillMaxSize(),state = listState, contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ){
        item{
            DescriptionRoom(title = place.room, description = place.descriptionRoom)
        }
        items(place.persons){
            DescriptionPerson(it)
        }
    }
}


@Composable
fun DescriptionRoom(title: String, description : String) {
    Card(
        Modifier.fillMaxWidth(),
        RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp , Purple700),
        elevation = 6.dp
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(24.dp)) {
            Text(title , fontFamily = oswald , fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(Modifier.height(8.dp))
            Text(description , style = MaterialTheme.typography.body1 ,  fontFamily = oswald , fontWeight = FontWeight.Light)
        }
    }
}

@Composable
fun DescriptionPerson(person: Person) {
    Card(
        Modifier.fillMaxWidth(),
        RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp , Purple700),
        elevation = 6.dp
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(24.dp)) {
            Text(person.name , fontFamily = oswald , fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(Modifier.height(8.dp))
            Text("${stringResource(R.string.сlasses)} - ${person.classPerson }" ,
                fontWeight = FontWeight.Bold, fontFamily = oswald ,style = MaterialTheme.typography.subtitle1)
            Text("${stringResource(R.string.item)} - ${person.item }" ,
                fontWeight = FontWeight.Bold, fontFamily = oswald , style = MaterialTheme.typography.subtitle1 )
            Spacer(Modifier.height(8.dp))
            Text(person.description, style = MaterialTheme.typography.body1 ,  fontFamily = oswald , fontWeight = FontWeight.Light)
        }
    }
}
