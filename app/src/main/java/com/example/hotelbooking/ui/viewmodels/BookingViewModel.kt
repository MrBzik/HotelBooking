package com.example.hotelbooking.ui.viewmodels

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelbooking.data.local.model.BookingInfoPresent
import com.example.hotelbooking.data.local.model.TouristInfo
import com.example.hotelbooking.usecases.UseCaseFetchBookingInfo
import com.example.hotelbooking.utils.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Stack
import javax.inject.Inject


@HiltViewModel
class BookingViewModel @Inject constructor(
    private val useCaseFetchBookingInfo : UseCaseFetchBookingInfo
): ViewModel() {

    private val _bookingInfoState = MutableStateFlow(BookingInfoPresent())
    val bookingInfoState = _bookingInfoState.asStateFlow()

    private val touristsLabels = ArrayDeque<String>().apply {
        add("Первый")
        add("Второй")
        add("Третий")
        add("Четвертый")
        add("Пятый")
        add("Шестой")
        add("Седьмой")
        add("Восьмой")
        add("Девятый")
        add("Десятый")
    }


    private val _touristsState = MutableStateFlow(listOf(
        TouristInfo(
            id = System.currentTimeMillis(),
            touristLabel = touristsLabels.removeFirst())))
    val touristInfo = _touristsState.asStateFlow()

    fun addNewTourist(){
        if(touristsLabels.isEmpty()) return

        val newList = _touristsState.value.toMutableList()
        newList.add(TouristInfo(
            id = System.currentTimeMillis(),
            touristLabel = touristsLabels.removeFirst()
        ))
        _touristsState.value = newList
    }


    init {
        viewModelScope.launch {
            _bookingInfoState.value = useCaseFetchBookingInfo.fetchBookingInfo()
        }
    }




    fun validatePhoneNumber(value: String) : Boolean {
        return value.length == 18
    }

    fun validateEmail(value: String) : Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(value).matches()
    }


}