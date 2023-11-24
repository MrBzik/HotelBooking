package com.example.hotelbooking.adapters.model

import com.example.hotelbooking.adapters.delegateAdapter.DelegateAdapterItem
import com.example.hotelbooking.domain.model.RoomPresent

data class RoomItem(
    val room : RoomPresent
) : DelegateAdapterItem {
    override fun id(): Any {
        return room.id
    }

    override fun content(): Any {
        return room.id
    }
}
