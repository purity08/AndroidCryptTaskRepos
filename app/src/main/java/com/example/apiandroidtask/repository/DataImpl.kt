package com.example.apiandroidtask.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.apiandroidtask.DI.App
import com.example.apiandroidtask.model.CryptIntervalData
import com.example.apiandroidtask.model.IntervalData
import com.example.apiandroidtask.model.Cryptocurrency
import com.example.apiandroidtask.model.CryptData
import com.example.apiandroidtask.network.APIService

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class DataImpl : Data {

    @Inject
    lateinit var apiService: APIService

    init {
        App.appComponent.inject(this)
    }

    override fun getCryptData(): LiveData<ArrayList<Cryptocurrency>> {
        val liveData = MutableLiveData<ArrayList<Cryptocurrency>>()

        apiService.getCryptList()
            .enqueue(object : Callback<CryptData> {
                override fun onResponse(
                    call: Call<CryptData>,
                    response: Response<CryptData>
                ) {
                    liveData.value = response.body()?.data
                }

                override fun onFailure(call: Call<CryptData>, t: Throwable) {
                }
            })

        return liveData
    }

    override fun getCryptDataWithInterval(id: String, start: Long, end: Long): LiveData<ArrayList<IntervalData>> {

        val live24hData = MutableLiveData<ArrayList<IntervalData>>()

        apiService.getCryptListWithInterval(id, start, end)
            .enqueue(object : Callback<CryptIntervalData> {
                override fun onResponse(call: Call<CryptIntervalData>, response: Response<CryptIntervalData>) {
                    live24hData.value = response.body()?.data
                }

                override fun onFailure(call: Call<CryptIntervalData>, t: Throwable) {}
            })
        return live24hData
    }

}