package com.example.hotelbooking.utils

import com.example.hotelbooking.domain.model.BookingInfoPresent

object BookingTourSum {

    fun getTotalBookingSum(info: BookingInfoPresent) : Int {
        return info.tourPrice + info.fuelCharge + info.serviceCharge
    }

}