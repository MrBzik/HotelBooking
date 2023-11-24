package com.example.hotelbooking.utils

object FieldsMasks {

    enum class Mask(val mask: String){
        PHONE("+7 ([000]) [000]-[00]-[00]"),
        DATE("[00]{.}[00]{.}[0000]")
    }
}