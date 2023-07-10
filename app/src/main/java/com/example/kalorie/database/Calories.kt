package com.example.kalorie.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


var currentDateTime = LocalDateTime.now()
var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

@Entity(tableName = "calories")
data class Calories(

 val names: String,
 val value: String,
 val date: String = currentDateTime.format(formatter),
 @PrimaryKey(autoGenerate = true)
 val id: Int = 0
)
