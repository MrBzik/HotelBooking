package com.example.hotelbooking.data.local

import com.example.hotelbooking.data.TouristsDataSource
import com.example.hotelbooking.domain.model.TouristInfo
import com.example.hotelbooking.utils.InputFields
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class TouristsDataSourceTestImpl : TouristsDataSource {



    override val touristsFlow
        get() = touristsStateFlow

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

    private val touristsStateFlow: MutableStateFlow<List<TouristInfo>> = MutableStateFlow(listOf(
        TouristInfo(
            id = System.currentTimeMillis(),
            touristLabel = touristsLabels.removeFirst()
        )))

    override suspend fun addNewTourist() {

        if(touristsLabels.isEmpty()) return

        val newList = touristsStateFlow.value.toMutableList()
        newList.add(
            TouristInfo(
                id = System.currentTimeMillis(),
                touristLabel = touristsLabels.removeFirst()
            )
        )
        touristsStateFlow.value = newList
    }

    override fun updateTouristInfo(value: String, index: Int, field: InputFields) {

        when(field){
            InputFields.FIRST_NAME ->  touristsStateFlow.value[index].fistName = value
            InputFields.LAST_NAME -> touristsStateFlow.value[index].lastName = value
            InputFields.DATE_OF_BIRTH -> touristsStateFlow.value[index].dateOfBirth = value
            InputFields.PASSPORT -> touristsStateFlow.value[index].passportNumber = value
            InputFields.PASSPORT_EXP -> touristsStateFlow.value[index].passportExpire = value
            InputFields.CITIZENSHIP -> touristsStateFlow.value[index].citizenship = value
            else -> {}
        }

    }

}