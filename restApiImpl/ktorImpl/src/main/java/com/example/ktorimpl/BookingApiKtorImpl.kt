package com.example.ktorimpl

import com.example.restapi.Endpoints
import com.example.restapi.datasource.BookingApi
import com.example.restapi.entities.BookingInfo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class BookingApiKtorImpl(private val client: HttpClient) : BookingApi {
    override suspend fun fetchBookingInfo(): BookingInfo? {
        return try {
            client.get(Endpoints.BaseUrl.url + Endpoints.BookingInfo.url).body()
        } catch (e : Exception){
            null
        }
    }
}