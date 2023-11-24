package com.example.restapi


sealed class Endpoints(val url : String){


    data object BaseUrl : Endpoints("https://run.mocky.io/v3/")

    data object HotelInfo : Endpoints("d144777c-a67f-4e35-867a-cacc3b827473")

    data object RoomInfo : Endpoints("8b532701-709e-4194-a41c-1a903af00195")

    data object BookingInfo : Endpoints("63866c74-d593-432c-af8e-f279d1a8d2ff")

}