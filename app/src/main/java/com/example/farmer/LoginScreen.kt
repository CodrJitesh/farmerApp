package com.example.farmer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.outlinedTextFieldColors
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.farmer.RetroFitWeather.WeatherViewModel
import com.example.farmer.forecast.ForecastViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(viewModel: WeatherViewModel){
    var city by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    var showIt by remember { mutableStateOf(true) }
    
    Box(
        modifier = Modifier
            .fillMaxSize().zIndex(if(showIt) 10f else 1f)
            .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null) {
                focusManager.clearFocus()  // Unfocus when clicking outside
                    
            },
    ) {

        FloatingActionButton(
            onClick = { showIt = false
                      viewModel.updateText(city)
                      },
            containerColor = Color.Black,
            shape = CircleShape,
            contentColor = Color.White,
            modifier = Modifier
                .padding(16.dp)
                .zIndex(2f)
                .align(Alignment.CenterEnd)
                // Add margin from the screen edge

        ) {
            Icon(Icons.Filled.ArrowForward, contentDescription = "Add")
        }



        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF1B4000),  // Very Dark Green
                            Color(0xFF265C00),  // Dark Green
                            Color(0xFF3D7A16),  // Deep Green
                            Color(0xFF599B34),  // Forest Green
                            Color(0xFF88C76A),  // Muted Green
                            Color(0xFFD6F5D6),  // Soft Greenish-White
                            Color(0xFFFDFFFF)
                        )
                    )
                )
        ) {
            Text(
                "नमस्ते!!",
                fontSize = 75.sp,
                color = Color.White,
                modifier = Modifier.padding(top = 65.dp).align(Alignment.CenterHorizontally)
            )
            Image(
                painter = painterResource(R.drawable.farmer),
                contentDescription = "",
                modifier = Modifier.size(450.dp).padding(0.dp)
            )
            OutlinedTextField(
                value = state,
                onValueChange = { state = it },
                label = { Text("Enter your state") },
                shape = RoundedCornerShape(50f),
                colors = outlinedTextFieldColors(
                    focusedTextColor = Color.Black,      // Text inside the field
                    cursorColor = Color.White,    // Cursor color
                    focusedBorderColor = Color.Black,  // White border when focused
                    unfocusedBorderColor = Color.Gray  // Gray border when not focused
                ),

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp, vertical = 0.dp)
            )

            OutlinedTextField(
                value = city,
                onValueChange = { city = it },
                label = { Text("Enter your city") },
                shape = RoundedCornerShape(50f),
                colors = outlinedTextFieldColors(
                    focusedTextColor = Color.Black,      // Text inside the field
                    cursorColor = Color.White,    // Cursor color
                    focusedBorderColor = Color.Black,  // White border when focused
                    unfocusedBorderColor = Color.Gray  // Gray border when not focused
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp, vertical = 20.dp)
            )

        }

    }

}