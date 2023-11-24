package com.example.hotelbooking.data.local.model

data class RoomPresent(
    val id: Int,
    val imageUrls: List<String>,
    val name: String,
    val peculiarities: List<String>,
    val price: Int,
    val pricePer: String
)
