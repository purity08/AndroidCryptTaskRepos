package com.example.apiandroidtask.repository

import androidx.lifecycle.LiveData
import com.example.apiandroidtask.model.Cryptocurrency
import com.example.apiandroidtask.model.IntervalData

interface Data {
    fun getCryptData(): LiveData<ArrayList<Cryptocurrency>>
    fun getCryptDataWithInterval(id: String, start: Long, end: Long): LiveData<ArrayList<IntervalData>>
}