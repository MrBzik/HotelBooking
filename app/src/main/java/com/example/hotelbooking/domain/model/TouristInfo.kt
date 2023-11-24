package com.example.hotelbooking.domain.model

data class TouristInfo(
    val id : Long,
    val touristLabel : String = "Первый турист",
    var fistName : String = "",
    var lastName : String = "",
    var dateOfBirth: String = "",
    var citizenship : String = "",
    var passportNumber : String = "",
    var passportExpire: String = "",
    var isExpanded : Boolean = true
)

