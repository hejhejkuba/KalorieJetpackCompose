package com.example.kalorie

import android.content.ContentValues
import com.example.kalorie.database.Calories

data class CaloriesState(
    val calories: List<Calories> = emptyList(),
    val names: String = "",
    val values: String = "",
    val date : String = "",
    val isAddingCalories: Boolean = false,
    val sortType: SortType = SortType.names
)
