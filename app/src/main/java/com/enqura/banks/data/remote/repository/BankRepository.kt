package com.enqura.banks.data.remote.repository

import androidx.lifecycle.MutableLiveData
import com.enqura.banks.data.remote.api.ApiClient
import com.enqura.banks.data.remote.api.ApiService
import com.enqura.banks.data.remote.response.BankResponse

class BankRepository(var client: ApiClient) {

    private val apiClient: ApiService? = ApiClient.getApiService()
    private val call: retrofit2.Call<BankResponse> = apiClient!!.getBankList()

    val liveDataBanks: MutableLiveData<BankResponse> = MutableLiveData()
    private var bankResponse: BankResponse? = null


    fun getBankList(): MutableLiveData<BankResponse> {
        call.enqueue(object : retrofit2.Callback<BankResponse> {
            override fun onResponse(
                call: retrofit2.Call<BankResponse>,
                response: retrofit2.Response<BankResponse>
            ) {
                if (response.isSuccessful) {
                    try {
                        if (response.code() == 200) {
                            bankResponse = response.body()
                        }
                        liveDataBanks.postValue(bankResponse)
                    } catch (e: Exception) {

                    }
                }
            }

            override fun onFailure(call: retrofit2.Call<BankResponse>, t: Throwable) {

            }
        })
        return liveDataBanks
    }
}