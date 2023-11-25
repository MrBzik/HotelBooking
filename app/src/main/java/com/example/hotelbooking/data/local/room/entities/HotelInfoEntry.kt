package com.example.hotelbooking.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HotelInfoEntry(
    val description: String,
    val peculiarities: List<String>,
    val address: String,
    @PrimaryKey
    val id: Int,
    val imageUrls: List<String>,
    val minimalPrice: Int,
    val name: String,
    val priceForIt: String,
    val rating: Int,
    val ratingName: String
)
