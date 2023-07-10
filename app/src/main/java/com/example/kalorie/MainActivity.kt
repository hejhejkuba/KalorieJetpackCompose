package com.example.kalorie

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.kalorie.bars.BottomBar
import com.example.kalorie.database.CaloriesDatabase
import com.example.kalorie.screens.HomeScreen
import com.example.kalorie.ui.theme.KalorieTheme



class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController




    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            CaloriesDatabase::class.java,
            "calories"
        )
            .fallbackToDestructiveMigration()
            .build();
    }
    private val viewModel by viewModels<CaloriesViewModel> (
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CaloriesViewModel(db.dao) as T
                }
            }
        }
    )

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KalorieTheme {
                val state by viewModel.state.collectAsState(initial = CaloriesState())
                val navController = rememberNavController()
                Scaffold(bottomBar = { BottomBar(navController = navController)}) {
                    BottomNavGraph(navController = navController, state = state, onEvent = viewModel::onEvent)
                }
            }
        }
    }


}
