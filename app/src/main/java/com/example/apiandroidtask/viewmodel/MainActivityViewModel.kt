package com.example.apiandroidtask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.apiandroidtask.model.IntervalData
import com.example.apiandroidtask.model.RecyclerData
import com.example.apiandroidtask.repository.DataInterfaceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class MainActivityViewModel @Inject constructor(): ViewModel() {

    @Provides
    @Singleton
    fun recyclerListData(): LiveData<ArrayList<RecyclerData>> = DataInterfaceImpl().getData()

    @Provides
    @Singleton
    fun recycler24hListData(id: String, start: Long, end: Long):
            LiveData<ArrayList<IntervalData>> =
        DataInterfaceImpl().get24hData(id,start,end)
}