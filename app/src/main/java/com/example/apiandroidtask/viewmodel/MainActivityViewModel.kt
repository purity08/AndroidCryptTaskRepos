package com.example.apiandroidtask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.apiandroidtask.model.IntervalData
import com.example.apiandroidtask.model.Cryptocurrency
import com.example.apiandroidtask.repository.DataImpl
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class MainActivityViewModel @Inject constructor(): ViewModel() {

    @Provides
    @Singleton
    fun cryptList(): LiveData<ArrayList<Cryptocurrency>> = DataImpl().getCryptData()

    @Provides
    @Singleton
    fun cryptListWithInterval(id: String, start: Long, end: Long):
            LiveData<ArrayList<IntervalData>> =
        DataImpl().getCryptDataWithInterval(id,start,end)
}