package com.example.restapi.datasource

import com.example.restapi.entities.RoomInfo


interface RoomsApi {

    suspend fun fetchListOfRooms() : RoomInfo?

}