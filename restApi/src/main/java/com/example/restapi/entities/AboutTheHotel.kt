package com.example.restapi.entities

import kotlinx.serialization.Serializable

@Serializable
data class AboutTheHotel(
    val description: String,
    val peculiarities: List<String>
)