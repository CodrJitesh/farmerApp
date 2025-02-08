package com.example.farmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.farmer.RetroFitWeather.WeatherViewModel
import com.example.feather.ui.theme.api.NetworkResponse
import com.example.feather.ui.theme.api.WeatherModel

@Composable
fun WeatherCurrent(viewModel: WeatherViewModel){
    var city by remember {
        mutableStateOf("Amritsar")
    }
    var gotTemp by remember { mutableStateOf(false) }

    if (!gotTemp){
        viewModel.getData(city)
        gotTemp = !gotTemp
    }



    val weatherResult = viewModel.weatherResult.observeAsState()

    Box(modifier = Modifier.fillMaxWidth().height(180.dp)){
        when (val result = weatherResult.value) {
            is NetworkResponse.Error -> Text(result.message)
            NetworkResponse.Loading -> Loading()
            is NetworkResponse.Success -> WeatherDetails(result.data)
            null -> {}
        }
    }
}

@Composable
fun Loading(){
    Box(
        modifier = Modifier.fillMaxSize().offset(y = (-50).dp),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator(modifier = Modifier.size(70.dp))
    }

}
@Composable
fun WeatherDetails(data: WeatherModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            Icon( // location icon
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location icon",
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = "${data.location.name},",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = data.location.country,
                modifier = Modifier.offset(y = (-3).dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Text(
                text = "${data.current.temp_c} ° C",
                fontSize = 64.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center

            )
            Spacer(modifier= Modifier.width(22.dp))
            Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                AsyncImage( // current weather icon
                    model = "https:${data.current.condition.icon}".replace("64x64", "128x128"),
                    contentDescription = "current weather icon",
                    modifier = Modifier.size(64.dp)
                )
                Text( // current weather descprition
                    text = data.current.condition.text,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Gray

                )
            }
        }

    }
}
