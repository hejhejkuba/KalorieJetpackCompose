package com.example.kalorie

import com.example.kalorie.database.Calories

sealed interface CaloriesEvent{
    object saveCalories: CaloriesEvent
    data class SetNames(val name: String): CaloriesEvent
    data class SetCalories(val values: String): CaloriesEvent
    object ShowDialog: CaloriesEvent
    object HideDialog: CaloriesEvent
    data class SortCalories(val sortType: SortType): CaloriesEvent
    data class DeleteCalories(val calories : Calories): CaloriesEvent
}