package com.example.farmer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.farmer.RetroFitWeather.WeatherViewModel
import com.example.farmer.ui.theme.FarmerTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val weatherViewModel = ViewModelProvider(owner = this)[WeatherViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            FarmerTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Farmer ka app") },
                            actions = {

                            },
                        )
                    },

                    content = { paddingValues ->
//                        Box(modifier = Modifier.fillMaxSize().background(Color.Gray)){
                        Column(
                            verticalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.padding(paddingValues)
                        ) {

                                WeatherCurrent(weatherViewModel)
                                FarmingTipsCarousel()
                                CommodityPriceScreen()
                            }

//                        }
                    }
                )
            }
        }
    }
}




