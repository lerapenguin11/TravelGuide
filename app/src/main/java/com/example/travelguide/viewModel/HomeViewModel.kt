package com.example.travelguide.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.travelguide.business.model.bd.AttractionDao
import com.example.travelguide.business.model.bd.AttractionDatabase
import com.example.travelguide.business.model.bd.AttractionModel
import com.example.travelguide.business.repos.AttractionRepository
import kotlinx.coroutines.launch

class HomeViewModel(/*private val repository: AttractionRepository*/
application: Application) : AndroidViewModel(application) {

    val repository : AttractionRepository
    private val dataList = MutableLiveData<List<AttractionModel>>()

    init {
        val dao = AttractionDatabase.getInstance(application).sightseeingDao()
        repository = AttractionRepository(dao)
    }

    var allSightseeing: LiveData<List<AttractionModel>> = repository.allSightseeing

    fun getDataList(): LiveData<List<AttractionModel>> {
        return dataList
    }

    fun setData(dataList: List<AttractionModel>) {
        this.dataList.value = dataList
    }

    fun insert(sightseeing: AttractionModel) = viewModelScope.launch {
        repository.insert(sightseeing)
    }

    fun update(sightseeing: AttractionModel) = viewModelScope.launch {
        repository.update(sightseeing)
    }

    fun delete(sightseeing: AttractionModel) = viewModelScope.launch {
        repository.delete(sightseeing)
    }

    fun getAll() = viewModelScope.launch {
        repository.getAll()
    }
}