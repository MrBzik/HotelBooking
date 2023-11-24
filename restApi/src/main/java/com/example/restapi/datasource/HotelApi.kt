package com.example.restapi.datasource

import com.example.restapi.entities.HotelInfo


interface HotelApi {

    suspend fun fetchHotelInfo() : HotelInfo?

}