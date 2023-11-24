package com.example.ktorimpl

import com.example.restapi.Endpoints
import com.example.restapi.datasource.RoomsApi
import com.example.restapi.entities.RoomInfo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import java.lang.Exception

class RoomsApiKtorImpl(private val client: HttpClient) : RoomsApi {

    override suspend fun fetchListOfRooms(): RoomInfo? {
        return try {
            client.get(Endpoints.BaseUrl.url + Endpoints.RoomInfo.url).body()
        } catch (e : Exception){
            null
        }
    }
}