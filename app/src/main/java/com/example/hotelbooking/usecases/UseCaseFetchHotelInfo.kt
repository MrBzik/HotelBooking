package com.example.hotelbooking.usecases

import com.example.hotelbooking.data.local.model.HotelInfoPresent
import com.example.hotelbooking.data.local.model.toHotelInfoPresent
import com.example.restapi.datasource.HotelApi

class UseCaseFetchHotelInfo (private val hotelApi: HotelApi) {

    suspend fun fetchHotelInfo() : HotelInfoPresent =
        hotelApi.fetchHotelInfo()?.toHotelInfoPresent() ?: HotelInfoPresent()

}