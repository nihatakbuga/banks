package com.enqura.banks.data.remote.api

import com.enqura.banks.data.remote.response.BankResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("fatiha380/mockjson/main/bankdata")
    fun getBankList(): Call<BankResponse>

}