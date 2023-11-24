package com.example.hotelbooking.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelbooking.data.local.model.RoomPresent
import com.example.hotelbooking.usecases.UseCaseGetRoomsList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomsViewModel @Inject constructor(
    private val useCaseGetRoomsList: UseCaseGetRoomsList
) : ViewModel() {


    private val _listOfRooms : MutableStateFlow<List<RoomPresent>> = MutableStateFlow(emptyList())
    val listOfRooms = _listOfRooms.asStateFlow()

    init {
        viewModelScope.launch {
            _listOfRooms.value = useCaseGetRoomsList.getRoomsList()
        }
    }
}