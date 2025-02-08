package com.example.farmer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FarmingTipsCarousel() {
    val itemCount = Int.MAX_VALUE
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(itemCount) { index ->
            val actualIndex = index % farmingTips.size
            FarmingTipCard(farmingTips[actualIndex])
        }
    }
}

@Composable
fun FarmingTipCard(tip: String) {
    Card(
        modifier = Modifier.height(100.dp)
            .padding(end = 8.dp)
            .width(250.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = tip,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

val farmingTips = listOf(
    "🌾 Wheat follows the rain—sow after a good monsoon for the best yield!",
    "🌦️ Rain coming? Skip irrigation today and save water for drier days!",
    "🐜 Neem oil is nature’s pesticide—use it to keep your crops pest-free!",
    "📈 Market tip: Tomatoes sell best in winter—plan your harvest accordingly!",
    "💧 Drip irrigation saves 50% more water than flood irrigation—be smart, save water!",
    "☀️ Too much sun? Mulching keeps the soil cool and locks in moisture!",
    "📊 Check mandi prices before selling—your crop might be worth more elsewhere!",
    "🌱 Legumes fix nitrogen in the soil—rotate them after cereal crops for healthy land!",
    "🚜 Plow before the first rain—soft soil absorbs more water and prevents runoff!",
    "🔥 Never burn stubble! Turn it into compost for rich, fertile soil.",
    "🥦 Leafy greens love shade—plant them under taller crops for a natural cover!",
    "🏡 Bees boost pollination—grow flowers nearby for a natural yield increase!",
    "🌕 Full moon planting? Some farmers swear by it for stronger crop growth!",
    "🏗️ Raised beds drain better—perfect for rainy areas and avoiding waterlogging!",
    "🌾 Late sowing? Choose early-maturing crop varieties to make up for lost time!",
    "🍌 Banana peels aren’t waste—they’re full of potassium for healthier plants!",
    "🚜 Smart farming: Drones can detect crop diseases early—tech meets tradition!",
    "🥔 Potatoes love loose soil—till well for bigger, better spuds!",
    "🌻 Sunflowers follow the sun—plant them where they get full sunlight!",
    "🧑‍🌾 Healthy soil = Healthy crops. Compost is gold for your farm!",
    "🌊 Water early morning or late evening—less evaporation, more hydration!",
    "🍅 Tomatoes hate too much nitrogen—too much and you’ll get leaves, not fruits!",
    "🌾 Harvest grains when they’re dry—moisture invites fungus and lowers quality!",
    "🌳 More trees, more rain—agroforestry improves soil and brings natural shade!",
    "🌱 More air circulation helps! Sow in rows, not clusters, to prevent diseases!",
    "🐛 Pests hate marigolds—plant them around your crops as a natural shield!"
)
