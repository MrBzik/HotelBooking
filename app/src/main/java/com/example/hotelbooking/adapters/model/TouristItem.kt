package com.example.hotelbooking.adapters.model

import com.example.hotelbooking.adapters.delegateAdapter.DelegateAdapterItem
import com.example.hotelbooking.domain.model.TouristInfo

data class TouristItem(val touristInfo: TouristInfo) : DelegateAdapterItem {

    override fun id(): Any {
        return touristInfo.id
    }

    override fun content(): Any {
        return touristInfo.isExpanded
    }
}
