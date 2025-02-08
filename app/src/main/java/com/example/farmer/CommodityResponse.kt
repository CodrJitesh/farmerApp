package com.example.farmer

data class CommodityResponse(
    val records: List<CommodityRecord>
)

data class CommodityRecord(
    val state: String,
    val district: String,
    val market: String,
    val commodity: String,
    val variety: String,
    val grade: String,
    val arrival_date: String,
    val min_price: Double,
    val max_price: Double,
    val modal_price: Double
)