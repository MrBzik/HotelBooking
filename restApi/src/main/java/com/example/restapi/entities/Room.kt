package com.example.restapi.entities

import kotlinx.serialization.Serializable

@Serializable
data class Room(
    val id: Int,
    val image_urls: List<String>,
    val name: String,
    val peculiarities: List<String>,
    val price: Int,
    val price_per: String
)