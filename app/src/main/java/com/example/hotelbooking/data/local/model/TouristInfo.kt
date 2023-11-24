package com.example.hotelbooking.data.local.model

data class TouristInfo(
    val id : Long,
    val touristLabel : String = "Первый турист",
    val fistName : String = "",
    val lastName : String = "",
    val dateOfBirth: String = "",
    val citizenship : String = "",
    val passportNumber : String = "",
    val passportExpire: String = "",
    var isExpanded : Boolean = true
)

