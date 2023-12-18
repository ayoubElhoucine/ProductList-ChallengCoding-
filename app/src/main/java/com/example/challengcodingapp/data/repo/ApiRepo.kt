package com.example.challengcodingapp.data.repo

import com.example.challengcodingapp.data.netowork.Api
import com.example.challengcodingapp.data.netowork.SafeApiCall
import javax.inject.Inject

class ApiRepo @Inject constructor(
    private val api: Api
) : SafeApiCall {

    suspend fun getItems() = safeApiCall(apiCall = api::getItems)

}