package com.example.kalorie.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.Room
import com.example.kalorie.AddContactDialog
import com.example.kalorie.BottomBarScreen
import com.example.kalorie.CaloriesEvent
import com.example.kalorie.CaloriesState
import com.example.kalorie.SortType
import com.example.kalorie.TopBar
import com.example.kalorie.charts.Chart
import com.example.kalorie.database.CaloriesDatabase
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController : NavController,
    state: CaloriesState,
    onEvent: (CaloriesEvent) -> Unit
) {
Scaffold(
    topBar = {  TopBar(title = "Dzisiejsze Kalorie") },
    floatingActionButton = {
    FloatingActionButton(modifier = Modifier
        .padding(vertical = 50.dp)
        .size(60.dp)
    ,onClick = {
        onEvent(CaloriesEvent.ShowDialog)
    }) {
    Icon(imageVector = Icons.Default.Add,
        contentDescription = "Add Calories")
}
}) {
    padding ->

    if(state.isAddingCalories)
    {
        AddContactDialog(state = state, onEvent = onEvent)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = padding), // use "it" parameter
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
       Row(
           modifier = Modifier
               .fillMaxWidth()
               .weight(3f)
       )
       {
           Chart(
               state
               , max_value = 2000)


       }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                ,
            horizontalArrangement = Arrangement.Center
        ) {
            var dataNow = LocalDate.now().toString()
            var suma : Int = 0
            state.calories.forEach { calories ->
                var pom = calories.date.split("\\s".toRegex())
                if (dataNow.equals(pom[0])) {
                    suma += calories.value.toInt()
                }
            }

                Text(text = "Łącznie dziś : " + suma.toString() + " kcal", Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(20.dp),
                    Color.White,
                    fontSize = 20.sp)
            

        }
    }

}
}
