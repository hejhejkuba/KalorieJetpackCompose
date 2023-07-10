package com.example.kalorie.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kalorie.AddContactDialog
import com.example.kalorie.BottomBarScreen
import com.example.kalorie.CaloriesEvent
import com.example.kalorie.CaloriesState
import com.example.kalorie.SortType
import com.example.kalorie.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArchiveScreen(
    navController : NavController,
    state: CaloriesState,
    onEvent: (CaloriesEvent) -> Unit
) {
    Scaffold(

       // modifier = Modifier.padding(16.dp),
        topBar = {  TopBar(title = "Archiwum") },
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            item{
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically                )
                {
                    SortType.values().forEach { sortType ->
                        Row (
                            modifier = Modifier
                                .padding(0.dp)
                                .clickable {
                                    onEvent(CaloriesEvent.SortCalories(sortType))
                                },
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            RadioButton(selected = state.sortType ==sortType,
                                onClick = { onEvent(CaloriesEvent.SortCalories(sortType)) }
                            )
                            Text(text = sortType.name)
                        }
                    }
                }
            }
            items(state.calories){
                    calories ->
                Row(
                    modifier = Modifier.fillMaxWidth()

                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row( modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.Bottom) {
                            Text(
                                text = "${calories.names} ",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = "${calories.value} kcal",
                                fontSize = 16.sp
                            )
                        }
                        Text(
                            text = "${calories.date}",
                            fontSize = 12.sp
                        )
                    }
                    IconButton(
                        onClick = { onEvent(CaloriesEvent.DeleteCalories(calories)) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Calories"
                        )
                    }
                }
            }
        }
    }
}
