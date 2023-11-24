package com.example.hotelbooking.data.local.model

import com.example.restapi.entities.BookingInfo
import com.example.restapi.entities.HotelInfo
import com.example.restapi.entities.Room


fun HotelInfo.toHotelInfoPresent() : HotelInfoPresent {

    return HotelInfoPresent(
        description = about_the_hotel.description,
        peculiarities = about_the_hotel.peculiarities,
        address = adress,
        id = id,
        imageUrls = image_urls,
        minimalPrice = minimal_price,
        name = name,
        priceForIt = price_for_it,
        rating = rating,
        ratingName = rating_name
    )
}



fun Room.toRoomPresent() : RoomPresent {
    return RoomPresent(
        id = id,
        imageUrls = image_urls,
        name = name,
        peculiarities = peculiarities,
        price = price,
        pricePer = price_per
    )
}


fun BookingInfo.toBookingInfoPresent() : BookingInfoPresent {
    return BookingInfoPresent(
        arrivalCountry = arrival_country,
        departure = departure,
        fuelCharge = fuel_charge,
        hotelAddress = hotel_adress,
        hotelName = hotel_name,
        id = id,
        numberOfNights = number_of_nights,
        nutrition = nutrition,
        rating = horating,
        ratingName = rating_name,
        room = room,
        serviceCharge = service_charge,
        tourDateStart = tour_date_start,
        tourDateStop = tour_date_stop,
        tourPrice = tour_price
    )
}