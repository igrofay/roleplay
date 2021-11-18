package com.role.play.feature.ui.widgets

import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.role.play.feature.ui.theme.Black900
import com.role.play.feature.ui.theme.Shapes

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownSelect(
    listSelect: List<String>, select: (value: String?)->Unit
) {
    var expandedState by remember { mutableStateOf(true) }
    val rotationState by animateFloatAsState(
        if(expandedState) 180f else 0f
    )
    var selected: String? by remember { mutableStateOf(null) }
    select(selected)
    Card(modifier = Modifier
        .fillMaxWidth()
        .animateContentSize(
            animationSpec = tween(easing = LinearOutSlowInEasing)
        ),  shape = Shapes.medium, border = BorderStroke(1.dp , Black900)  ,
        onClick = { expandedState = !expandedState}
    ){
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)){
            Row(verticalAlignment = Alignment.CenterVertically){
                selected?.let {
                    Text(
                        it, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.h6,
                        modifier = Modifier.weight(1f), maxLines = 1 )
                } ?: Spacer(Modifier.weight(1f))
                Icon(Icons.Default.ArrowDropDown, contentDescription = null , Modifier.rotate(rotationState))
            }
            if(expandedState){
                Spacer(Modifier.height(8.dp))
                listSelect.forEach {
                    Text(it, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                expandedState = false
                                selected = it
                            }
                            .padding(vertical = 10.dp), maxLines = 1)
                }
            }
        }
    }

}