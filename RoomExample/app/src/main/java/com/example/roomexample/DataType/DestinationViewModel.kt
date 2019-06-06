package com.example.roomexample.DataType

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DestinationViewModel(application: Application): AndroidViewModel(application) {
    private val repository: DestinationRepository
    val allDestinations: LiveData<List<Destination>>

    init {
        val destinationDao = DestinationDatabase.getDatabase(application).destinationDao()
        repository = DestinationRepository(destinationDao)
        allDestinations = repository.allDestinations
    }

    fun insert(destination: Destination) = viewModelScope.launch(Dispatchers.IO) {
        Log.d("log_tag", destination.placeName)
        repository.insert(destination)
    }

    fun delete(destination: Destination) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(destination)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }
}