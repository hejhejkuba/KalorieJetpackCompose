package com.example.kalorie.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface CaloriesDao{
    @Query("SELECT * FROM calories")
     fun readAll(): Flow<List<Calories>>

    @Upsert
    suspend fun upsertCalories(calories: Calories)

    @Delete
    suspend fun deleteCalories(calories: Calories)

    @Query("SELECT * FROM calories ORDER BY date DESC")
     fun getCaloriesOrderedByDate(): Flow<List<Calories>>

    @Query("SELECT * FROM calories ORDER BY names ASC")
     fun getCaloriesOrderedByNames(): Flow<List<Calories>>

    @Query("SELECT * FROM calories ORDER BY value ASC")
     fun getCaloriesOrderedByValues(): Flow<List<Calories>>

}