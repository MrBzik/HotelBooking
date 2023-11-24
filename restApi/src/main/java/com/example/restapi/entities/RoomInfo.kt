package com.example.restapi.entities

import com.example.restapi.entities.Room
import kotlinx.serialization.Serializable

@Serializable
data class RoomInfo(
    val rooms: List<Room>
)