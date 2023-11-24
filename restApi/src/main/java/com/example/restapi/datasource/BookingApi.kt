package com.example.restapi.datasource

import com.example.restapi.entities.BookingInfo


interface BookingApi {

    suspend fun fetchBookingInfo() : BookingInfo?

}