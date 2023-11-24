package com.example.restapi.entities

import com.example.restapi.entities.AboutTheHotel
import kotlinx.serialization.Serializable

@Serializable
data class HotelInfo(
    val about_the_hotel: AboutTheHotel,
    val adress: String,
    val id: Int,
    val image_urls: List<String>,
    val minimal_price: Int,
    val name: String,
    val price_for_it: String,
    val rating: Int,
    val rating_name: String
)