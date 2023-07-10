package com.example.kalorie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactDialog(
    state: CaloriesState,
    onEvent: (CaloriesEvent) -> Unit,
    modifier: Modifier = Modifier
) {
AlertDialog(
    modifier = modifier,
    onDismissRequest = {
                       onEvent(CaloriesEvent.HideDialog)
                       },
    title = { Text(text = "Add Calories")},
    text = {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(
                value = state.names,
                onValueChange = { onEvent(CaloriesEvent.SetNames(it)) },
                placeholder = { Text(text = "name") }
            )
            TextField(
                value = state.values,
                onValueChange = { onEvent(CaloriesEvent.SetCalories(it)) },
                placeholder = { Text(text = "calories") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }



    },
    confirmButton = {
        Box(
            modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd
        )
        {
            Button(onClick = { onEvent(CaloriesEvent.saveCalories) })
            {
                Text(text = "Add")
            }
        }
    }
)
}