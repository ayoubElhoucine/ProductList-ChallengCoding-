package com.example.challengcodingapp.data.netowork

import com.example.challengcodingapp.model.Item
import retrofit2.http.GET

interface Api {
    @GET("items.json")
    suspend fun getItems(): List<Item>?

}