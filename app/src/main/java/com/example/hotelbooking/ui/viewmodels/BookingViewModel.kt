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


    val touristsState = touristsDataSource.touristsFlow.asStateFlow()




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


        touristsState.value.forEachIndexed { index, touristInfo ->

            if(touristInfo.fistName.isBlank()) errorChannel.send(BookingError(index, InputFields.FIRST_NAME)).also { isSuccess = false
            delay(100)
            }
            if(touristInfo.lastName.isBlank()) errorChannel.send(BookingError(index, InputFields.LAST_NAME)).also { isSuccess = false
            delay(100)
            }
            if(touristInfo.passportNumber.isBlank()) errorChannel.send(BookingError(index, InputFields.PASSPORT)).also { isSuccess = false
            delay(100)
            }
            if(touristInfo.passportExpire.isBlank()) errorChannel.send(BookingError(index, InputFields.PASSPORT_EXP)).also { isSuccess = false
            delay(100)
            }
            if(touristInfo.citizenship.isBlank()) errorChannel.send(BookingError(index, InputFields.CITIZENSHIP)).also { isSuccess = false
            delay(100)
            }
            if(touristInfo.dateOfBirth.isBlank()) errorChannel.send(BookingError(index, InputFields.DATE_OF_BIRTH)).also { isSuccess = false
            delay(100)
            }
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