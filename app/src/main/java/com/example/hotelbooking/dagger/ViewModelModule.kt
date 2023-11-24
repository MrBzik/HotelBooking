package com.example.hotelbooking.dagger

import com.example.restapi.datasource.BookingApi
import com.example.ktorimpl.BookingApiKtorImpl
import com.example.restapi.datasource.HotelApi
import com.example.ktorimpl.HotelApiKtorImpl
import com.example.restapi.datasource.RoomsApi
import com.example.ktorimpl.RoomsApiKtorImpl
import com.example.hotelbooking.usecases.UseCaseFetchBookingInfo
import com.example.hotelbooking.usecases.UseCaseFetchHotelInfo
import com.example.hotelbooking.usecases.UseCaseGetRoomsList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import io.ktor.client.HttpClient

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {


    @Provides
    @ViewModelScoped
    fun providesHotelApi(client: HttpClient) : HotelApi {
        return HotelApiKtorImpl(client)
    }

    @Provides
    @ViewModelScoped
    fun providesHotelUseCase(hotelApi: HotelApi) : UseCaseFetchHotelInfo {
        return UseCaseFetchHotelInfo(hotelApi)
    }


    @Provides
    @ViewModelScoped
    fun providesRoomsApi(client: HttpClient) : RoomsApi {
        return RoomsApiKtorImpl(client)
    }

    @Provides
    @ViewModelScoped
    fun providesRoomsUseCase(roomsApi: RoomsApi) : UseCaseGetRoomsList {
        return UseCaseGetRoomsList(roomsApi)
    }


    @Provides
    @ViewModelScoped
    fun providesBookingApi(client: HttpClient) : BookingApi {
        return BookingApiKtorImpl(client)
    }

    @Provides
    @ViewModelScoped
    fun providesUseCaseFetchBookingInfo(bookingApi: BookingApi) : UseCaseFetchBookingInfo {
        return UseCaseFetchBookingInfo(bookingApi)
    }


}