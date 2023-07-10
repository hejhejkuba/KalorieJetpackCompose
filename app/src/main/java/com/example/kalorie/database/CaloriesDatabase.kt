package com.example.kalorie.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Calories::class],
    version = 2
    //exportSchema = true
)
abstract class CaloriesDatabase : RoomDatabase(){

    abstract val dao : CaloriesDao


}