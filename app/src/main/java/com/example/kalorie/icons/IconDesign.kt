package com.example.kalorie.icons

import android.graphics.drawable.Icon
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IconDesign(icon: ImageVector, content : String)
{
 var clicked by remember { mutableStateOf(false) }
   Surface(
    onClick = {clicked = !clicked},
    shape = RoundedCornerShape(4.dp),
    border = BorderStroke(1.dp, color = Color.LightGray),
    color = MaterialTheme.colorScheme.surface
   ) {
        Box(modifier = Modifier.padding(5.dp)) {

            if (clicked)
            {
                Icon(imageVector = icon, contentDescription = content, tint = Color.LightGray)
            }
            else
            {
                Icon(imageVector = icon, contentDescription = content)
            }

        }
   }

}

@Composable
@Preview
fun PreviewIcon()
{
 IconDesign(icon = Icons.Default.Home, content = "Home")
}