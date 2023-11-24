package com.example.hotelbooking.domain.model

data class HotelInfoPresent(
    val description: String = "",
    val peculiarities: List<String> = emptyList(),
    val address: String = "",
    val id: Int = 0,
    val imageUrls: List<String> = emptyList(),
    val minimalPrice: Int = 0,
    val name: String = "",
    val priceForIt: String = "",
    val rating: Int = 0,
    val ratingName: String = ""
)