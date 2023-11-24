package com.example.hotelbooking.usecases

import com.example.hotelbooking.domain.model.RoomPresent
import com.example.hotelbooking.domain.toRoomPresent
import com.example.restapi.datasource.RoomsApi

class UseCaseGetRoomsList(private val roomsApi: RoomsApi) {

    suspend fun getRoomsList() : List<RoomPresent> =
        roomsApi.fetchListOfRooms()?.rooms?.map {
        it.toRoomPresent()
    } ?: emptyList()

}