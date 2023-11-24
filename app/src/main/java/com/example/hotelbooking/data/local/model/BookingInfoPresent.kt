package com.example.hotelbooking.data.local.model

data class BookingInfoPresent(
    val arrivalCountry: String = "",
    val departure: String = "",
    val fuelCharge: Int = 0,
    val rating: Int = 0,
    val hotelAddress: String = "",
    val hotelName: String = "",
    val id: Int = 0,
    val numberOfNights: Int = 0,
    val nutrition: String = "",
    val ratingName: String = "",
    val room: String = "",
    val serviceCharge: Int = 0,
    val tourDateStart: String = "",
    val tourDateStop: String = "",
    val tourPrice: Int = 0
)
