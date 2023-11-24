package com.example.hotelbooking.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelbooking.domain.model.HotelInfoPresent
import com.example.hotelbooking.usecases.UseCaseFetchHotelInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelViewModel @Inject constructor(
    private val getHotelInfo: UseCaseFetchHotelInfo
) : ViewModel() {


    private val _hotelInfoState = MutableStateFlow(HotelInfoPresent())
    val hotelInfoState = _hotelInfoState.asStateFlow()

    init {
        viewModelScope.launch {
            _hotelInfoState.value = getHotelInfo.fetchHotelInfo()
        }
    }


}