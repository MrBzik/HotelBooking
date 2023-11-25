package com.example.hotelbooking.ui.viewmodels

import android.text.Editable
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelbooking.data.TouristsDataSource
import com.example.hotelbooking.domain.model.BookingError
import com.example.hotelbooking.domain.model.BookingInfoPresent
import com.example.hotelbooking.domain.model.TouristInfo
import com.example.hotelbooking.usecases.UseCaseFetchBookingInfo
import com.example.hotelbooking.utils.InputFields
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BookingViewModel @Inject constructor(
    private val useCaseFetchBookingInfo : UseCaseFetchBookingInfo,
    private val touristsDataSource: TouristsDataSource
): ViewModel() {

    private val _bookingInfoState = MutableStateFlow(BookingInfoPresent())
    val bookingInfoState = _bookingInfoState.asStateFlow()


    val errorChannel = Channel<BookingError>()


    val touristsState = touristsDataSource.touristsFlow




    private var isEmailValid = false
    private var isPhoneValid = false

    fun addNewTourist() = viewModelScope.launch{

        touristsDataSource.addNewTourist()
    }


    init {
        viewModelScope.launch {
            _bookingInfoState.value = useCaseFetchBookingInfo.fetchBookingInfo()
        }
    }



    fun updateFieldValue(value: Editable?, index: Int, field: InputFields){

        if (value == null) return
        touristsDataSource.updateTouristInfo(value.toString(), index, field)
    }


    fun validatePhoneNumber(value: String) : Boolean {
        val check = value.length == 18
        isPhoneValid = check
        return check
    }

    fun validateEmail(value: String) : Boolean{
        val check = Patterns.EMAIL_ADDRESS.matcher(value).matches()
        isEmailValid = check
        return check
    }



    fun validatePayment(onSuccess : (Boolean) -> Unit) = viewModelScope.launch {

        var isSuccess = true


        suspend fun check (value: String, field: InputFields, index: Int){
            if(value.isBlank()){
                errorChannel.send(BookingError(index, field))
                isSuccess = false
                delay(20)
            }
        }

        touristsState.value.forEachIndexed { index, touristInfo ->

            check(touristInfo.fistName, InputFields.FIRST_NAME, index)
            check(touristInfo.lastName, InputFields.LAST_NAME, index)
            check(touristInfo.passportNumber, InputFields.PASSPORT, index)
            check(touristInfo.passportExpire, InputFields.PASSPORT_EXP, index)
            check(touristInfo.citizenship, InputFields.CITIZENSHIP, index)
            check(touristInfo.dateOfBirth, InputFields.DATE_OF_BIRTH, index)

        }


        if(!isEmailValid) errorChannel.send(BookingError(0, InputFields.EMAIL)).also { isSuccess = false
            delay(100)
        }

        if(!isPhoneValid) errorChannel.send(BookingError(0, InputFields.PHONE)).also { isSuccess = false
            delay(100)
        }

        onSuccess(isSuccess)

    }



}