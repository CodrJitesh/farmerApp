package com.example.farmer

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CommodityPriceScreen() {
    var prices by remember { mutableStateOf<List<CommodityRecord>>(emptyList()) }

    LaunchedEffect(Unit) {
        RetrofitClient.instance.getCommodityPrices(
            apiKey = "579b464db66ec23bdd0000012a1004d741f446936ad514dcaa3a4642",
            state = "Assam",
//            district = "amritsar"
        ).enqueue(object : Callback<CommodityResponse> {
            override fun onResponse(call: Call<CommodityResponse>, response: Response<CommodityResponse>) {
                if (response.isSuccessful) {
                    Log.d("myTag", "Response: ${response.body()}")
                    prices = response.body()?.records ?: emptyList()
                } else {
                    Log.e("myTag", "API call failed: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<CommodityResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }

    if (prices.isEmpty()) {
        Text("Loading...")
    } else {
        LazyColumn {
            items(prices) { record ->
                ItemRow(record)
            }
        }
    }
}

@Composable
fun ItemRow(record: CommodityRecord){
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        ItemCard(record)
    }
}
@Composable
fun ItemCard(record: CommodityRecord){
    Box( modifier = Modifier.padding(10.dp)

    ){
        Box(
            modifier = Modifier.height(140.dp).background(Color.Gray).fillMaxWidth().padding(10.dp), contentAlignment = Alignment.Center
        ){
            Column {
                Text("${record.commodity}")
                Text("${record.modal_price}")
                Text("${record.market}")

            }
        }
    }

}