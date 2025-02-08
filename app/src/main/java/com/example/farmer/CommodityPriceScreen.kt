package com.example.farmer

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import getDummyData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CommodityPriceScreen() {
    var prices by remember { mutableStateOf<List<CommodityRecord>>(emptyList()) }

    LaunchedEffect(Unit) {
        Log.d("response", "calling that fucking bastard to get data")
        RetrofitClient.instance.getCommodityPrices(
            apiKey = "579b464db66ec23bdd000001342e82881cfd450979143a47d0928b28",
            state = "Punjab",
//            district = "amritsar"
        ).enqueue(object : Callback<CommodityResponse> {
            override fun onResponse(
                call: Call<CommodityResponse>,
                response: Response<CommodityResponse>
            ) {
                if (response.isSuccessful) {
                    Log.d("response", "Response: ${response.body()}")
                    prices = response.body()?.records ?: emptyList()
                } else {
                    Log.e("response", "API call failed: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<CommodityResponse>, t: Throwable) {

            }
        })
    }

    if (prices.isEmpty()) {
        prices = getDummyData()
        LazyColumn {
            items(prices) { record ->
                ItemRow(record)
            }
        }
//        Loading()
    } else {
        LazyColumn {
            items(prices) { record ->
                ItemRow(record)
            }
        }
    }
}

@Composable
fun ItemRow(record: CommodityRecord) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        Box(
            modifier = Modifier.padding(10.dp)

        ) {
            Box(
                modifier = Modifier
                    .height(140.dp)
                    .clip(RoundedCornerShape(48f))
                    .background(MaterialTheme.colorScheme.surfaceDim)
                    .fillMaxWidth()
                    .padding(10.dp), contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly) {

                    Text("${record.commodity}")


                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom) {
                        Text("₹ ${record.min_price.toInt()}", color = Color(0xFF25b800), fontSize = 26.sp)
                        Text("₹ ${record.modal_price.toInt()}", fontSize = 40.sp)
                        Text("₹ ${record.max_price.toInt()}", color = Color(0xFFd40606), fontSize = 26.sp)
                    }





                }
            }
        }
    }
}
