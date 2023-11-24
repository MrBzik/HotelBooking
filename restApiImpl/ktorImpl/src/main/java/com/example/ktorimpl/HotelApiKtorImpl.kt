package com.example.ktorimpl

import android.util.Log
import com.example.restapi.Endpoints
import com.example.restapi.datasource.HotelApi
import com.example.restapi.entities.HotelInfo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import java.lang.Exception

class HotelApiKtorImpl(private val client: HttpClient) : HotelApi {

    override suspend fun fetchHotelInfo(): HotelInfo? {
        return try {
            client.get(Endpoints.BaseUrl.url + Endpoints.HotelInfo.url).body()
        } catch (e : Exception){
            Log.d("CHECKTAGS", e.stackTraceToString())
            null
        }
    }
}