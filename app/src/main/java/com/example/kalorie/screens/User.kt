package com.example.kalorie.screens

import android.content.Context
import android.content.Context.BATTERY_SERVICE
import android.content.Context.SENSOR_SERVICE
import android.content.Intent
import android.content.IntentFilter
import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.BatteryManager
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kalorie.CaloriesEvent
import com.example.kalorie.CaloriesState
import com.example.kalorie.TopBar



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfile (
    navController : NavController,
    state: CaloriesState,
    onEvent: (CaloriesEvent) -> Unit
) {
    Scaffold(
        topBar = { TopBar(title = "Statystyki") }
    ) {
            padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = padding), // use "it" parameter
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val openDialog = remember { mutableStateOf(false)  }
            val context = LocalContext.current
            Row()
            {
                Text(text = "Dodane aktualnie rekordy : "  + (state.calories.size).toString())
            }
            Row() {

                Button(onClick = { openDialog.value = true}) {
                    Image(imageVector = Icons.Default.Delete, contentDescription = "Delete All")
                    Text(text = "Usuń wszystkie rekordy",Modifier.padding(start = 10.dp))
                    if (openDialog.value) {

                        AlertDialog(
                            onDismissRequest = {
                                // Dismiss the dialog when the user clicks outside the dialog or on the back
                                // button. If you want to disable that functionality, simply use an empty
                                // onCloseRequest.
                                openDialog.value = false
                            },
                            title = {
                                Text(text = "Usunąć Wszystko ? ")
                            },
                            text = {
                                Text("Działanie nieodwracalne")
                            },
                            confirmButton = {
                                Button(

                                    onClick = {
                                        state.calories.forEach {
                                            calories ->
                                            onEvent(CaloriesEvent.DeleteCalories(calories))
                                        }
                                        mToast(context)
                                        openDialog.value = false
                                    }) {
                                    Text("Akceptuj")
                                }
                            },
                            dismissButton = {
                                Button(

                                    onClick = {
                                        openDialog.value = false
                                    }) {
                                    Text("Anuluj")
                                }
                            }
                        )
                    }
                }
            }
            Row() {
                displaySensors()
            }
        }
    }

}
@Composable
fun displaySensors()
{
    val ctx = LocalContext.current
    var sensorManager: SensorManager =
        ctx.getSystemService(SENSOR_SERVICE) as SensorManager
    var deviceSensors: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_LIGHT)
    var bm by remember { mutableStateOf(ctx.getSystemService(BATTERY_SERVICE) as BatteryManager) }
    var batLevel by remember { mutableStateOf(bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)) }

    var percentage: Int = batLevel/10

    var sensorsData = ""
    // on below line adding all sensors from
    // device sensors in our variable.
    //val bm = ctx.getSystemService(BATTERY_SERVICE) as BatteryManager

    // Get the battery percentage and store it in a INT variable
    //val batLevel:Int = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
    for (sens in deviceSensors) {
        sensorsData = sensorsData + sens.name + "  \n\n"
    }
    Column() {
        Row(modifier = Modifier.
        fillMaxWidth(),
        horizontalArrangement = Arrangement.Center) {
            Text(text = "Battery : " + batLevel + " %\n")
        }
        Row(modifier = Modifier.
        fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            Box(modifier = Modifier
                .size(200.dp, 100.dp)
                .background(color = Color.LightGray).border(BorderStroke(2.dp, Color.Black) )) {
                    Box(modifier = Modifier
                        .background(color = Color.Green)
                        .size((20*percentage).dp, 100.dp)

                        )
                }


        }
    }



}
fun mToast(context: Context){
    Toast.makeText(context, "Pomyślnie Usunieto", Toast.LENGTH_LONG).show()
}

