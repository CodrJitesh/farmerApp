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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.ViewModelProvider
import com.example.farmer.RetroFitWeather.WeatherViewModel
import com.example.farmer.forecast.ForecastViewModel
import com.example.farmer.ui.theme.FarmerTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val weatherViewModel = ViewModelProvider(owner = this)[WeatherViewModel::class.java]
        val foreCastViewModel = ViewModelProvider(owner = this)[ForecastViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            FarmerTheme {

                        LoginScreen(weatherViewModel)
                        Box(modifier = Modifier.fillMaxSize().zIndex(2f).background(Color(0xFFfaf0e6))
//                            .background(
//                            brush = Brush.verticalGradient(
//                                colors = listOf(
//                                    Color(0xFFF1F8F4), // Almost White with a hint of green
//                                    Color(0xFFE8F5E9), // Very Light Green
//                                    Color(0xFFD7ECD9), // Soft Pastel Green
//                                    Color(0xFFC8E6C9)  // Light Green
//                                )
//                            )
//                        )
                        ){
//                            Box(
//                                Modifier.height(290.dp).fillMaxSize().clip(CircleShape).background(Color(0xFF6B8E23))
//                            )
                            Column(
                                verticalArrangement = Arrangement.SpaceBetween,
//                                modifier = Modifier.padding(paddingValues)
                                modifier = Modifier.padding(top = 40.dp)
                            ) {
                                    WeatherCurrent(weatherViewModel, foreCastViewModel)
                                    FarmingTipsCarousel()
                                    CommodityPriceScreen()
                            }


                        }
                    }

            }
        }
    }





