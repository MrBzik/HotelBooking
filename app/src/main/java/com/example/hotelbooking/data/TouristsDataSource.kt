package com.example.hotelbooking.data

import com.example.hotelbooking.domain.model.TouristInfo
import com.example.hotelbooking.utils.InputFields
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface TouristsDataSource {

    val touristsFlow : MutableStateFlow<List<TouristInfo>>
    suspend fun addNewTourist()

    fun updateTouristInfo(value: String, index: Int, field: InputFields)

    suspend fun getListOfTourists()


}