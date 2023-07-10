package com.example.kalorie.charts

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.kalorie.CaloriesState
import java.time.LocalDate

@Composable
fun Chart(
    state: CaloriesState,
    max_value: Int
) {
    var dataNow = LocalDate.now().toString()
    val context = LocalContext.current
    // BarGraph Dimensions
    val barGraphHeight by remember { mutableStateOf(200.dp) }
    val barGraphWidth by remember { mutableStateOf(20.dp) }
    // Scale Dimensions
    val scaleYAxisWidth by remember { mutableStateOf(50.dp) }
    val scaleLineWidth by remember { mutableStateOf(2.dp) }
    //Outputs
    var textChange_ by remember { mutableStateOf("Kliknij na kolumny żeby wyświetlić szczegóły")}

    Column(
        modifier = Modifier
            .padding(50.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(barGraphHeight),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Start
        ) {
            // scale Y-Axis
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(scaleYAxisWidth),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(text = max_value.toString())
                    Spacer(modifier = Modifier.fillMaxHeight())
                }

                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(text = (max_value / 2).toString())
                    Spacer(modifier = Modifier.fillMaxHeight(0.5f))
                }

            }

            // Y-Axis Line
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(scaleLineWidth)
                    .background(Color.Black)
            )

            // graph
            state.calories.forEach {
                calories ->
                var pom = calories.date.split("\\s".toRegex())
                var fraction : Float = (calories.value.toFloat() / max_value)
                if(dataNow.equals(pom[0]))
                {
                    Box(
                        modifier = Modifier
                            .padding(start = barGraphWidth, bottom = 5.dp)
                            .clip(CircleShape)
                            .width(barGraphWidth)
                            .fillMaxHeight(fraction)
                            .background(MaterialTheme.colorScheme.primary)
                            .clickable {
                                textChange_ = calories.names + " " + calories.value + " kcal"
                            }
                    )
                }

            }

        }

        // X-Axis Line
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(scaleLineWidth)
                .background(Color.Black)
        )

        Row( modifier = Modifier.fillMaxWidth().padding(2.dp, top = 30.dp).clip(CircleShape).background(MaterialTheme.colorScheme.primary),
        horizontalArrangement = Arrangement.Center,
        ) {
            Text(text = textChange_, Modifier.padding(12.dp), Color.White)
        }
        // Scale X-Axis
//        Row(
//            modifier = Modifier
//                .padding(start = scaleYAxisWidth+barGraphWidth+scaleLineWidth)
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.spacedBy(barGraphWidth)
//        ) {
//
//            state.calories.forEach {
//                calories ->
//                var pom = calories.date.split("\\s".toRegex())
//                if(dataNow.equals(pom[0]))
//                {
//                    Text(
//                        modifier = Modifier.width(barGraphWidth),
//                        text = calories.names,
//                        textAlign = TextAlign.Center
//                    )
//                }
//
//            }
//
//        }

    }

}
