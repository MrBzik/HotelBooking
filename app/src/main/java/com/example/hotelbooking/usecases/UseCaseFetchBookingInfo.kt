package com.example.hotelbooking.usecases

import com.example.hotelbooking.domain.model.BookingInfoPresent
import com.example.hotelbooking.domain.toBookingInfoPresent
import com.example.restapi.datasource.BookingApi

class UseCaseFetchBookingInfo (private val bookingApi: BookingApi) {

    suspend fun fetchBookingInfo() : BookingInfoPresent =
        bookingApi.fetchBookingInfo()?.toBookingInfoPresent() ?: BookingInfoPresent()

}