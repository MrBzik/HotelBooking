package com.example.hotelbooking.data.local

import com.example.hotelbooking.data.TouristsDataSource
import com.example.hotelbooking.domain.model.TouristInfo
import com.example.hotelbooking.utils.InputFields
import kotlinx.coroutines.flow.MutableStateFlow

class TouristsDataSourceTestImpl : TouristsDataSource {

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

    override val touristsFlow: MutableStateFlow<List<TouristInfo>> = MutableStateFlow(listOf(
        TouristInfo(
            id = System.currentTimeMillis(),
            touristLabel = touristsLabels.removeFirst()
        )))

    override suspend fun addNewTourist() {

        if(touristsLabels.isEmpty()) return

        val newList = touristsFlow.value.toMutableList()
        newList.add(
            TouristInfo(
                id = System.currentTimeMillis(),
                touristLabel = touristsLabels.removeFirst()
            )
        )
        touristsFlow.value = newList
    }

    override fun updateTouristInfo(value: String, index: Int, field: InputFields) {

        when(field){
            InputFields.FIRST_NAME ->  touristsFlow.value[index].fistName = value
            InputFields.LAST_NAME -> touristsFlow.value[index].lastName = value
            InputFields.DATE_OF_BIRTH -> touristsFlow.value[index].dateOfBirth = value
            InputFields.PASSPORT -> touristsFlow.value[index].passportNumber = value
            InputFields.PASSPORT_EXP -> touristsFlow.value[index].passportExpire = value
            InputFields.CITIZENSHIP -> touristsFlow.value[index].citizenship = value
            else -> {}
        }

    }

    override suspend fun getListOfTourists() {
        touristsFlow.value = touristsFlow.value.toMutableList()
    }
}