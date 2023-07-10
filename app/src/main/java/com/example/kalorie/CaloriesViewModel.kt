package com.example.kalorie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kalorie.database.Calories
import com.example.kalorie.database.CaloriesDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class CaloriesViewModel(
    private val dao : CaloriesDao
) : ViewModel() {
    private val _sortType = MutableStateFlow(SortType.names)
    private val _calories = _sortType
        .flatMapLatest { _sortType ->
            when(_sortType){
                SortType.calories -> dao.getCaloriesOrderedByValues()
                SortType.names -> dao.getCaloriesOrderedByNames()
                SortType.date -> dao.getCaloriesOrderedByDate()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
        private val _state = MutableStateFlow(CaloriesState())
        val state = combine(_state, _sortType, _calories) { state, sortType, calories ->
            state.copy(
                calories = calories,
                sortType = sortType
            )

        }


    fun onEvent(event: CaloriesEvent)
    {
        when(event){
            is CaloriesEvent.DeleteCalories -> {
                viewModelScope.launch {
                    dao.deleteCalories(event.calories)
                }

            }
            CaloriesEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingCalories = false
                ) }
            }
            is CaloriesEvent.SetCalories -> {
                _state.update { it.copy(
                    values = event.values
                ) }
            }
            is CaloriesEvent.SetNames -> {
                _state.update { it.copy(
                    names = event.name
                ) }
            }
            CaloriesEvent.ShowDialog -> {
                _state.update { it.copy(
                    isAddingCalories = true
                ) }
            }
            is CaloriesEvent.SortCalories -> {
                _sortType.value = event.sortType
            }
            CaloriesEvent.saveCalories -> {
                val names = _state.value.names
                val values = _state.value.values

                if (names.isBlank() || values.isBlank())
                {
                    return
                }
                val calories = Calories(
                    names = names,
                    value = values,
                )
                viewModelScope.launch {
                    dao.upsertCalories(calories)
                }
                _state.update {
                    it.copy(
                        isAddingCalories = false,
                        names = "",
                        values = "",
                    )
                }
            }
        }
    }
}