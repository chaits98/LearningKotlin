package com.example.roomexample.DataType

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class DestinationRepository(private val destinationDao: DestinationDao) {

    val allDestinations: LiveData<List<Destination>> = destinationDao.getAllDestinations()

    @WorkerThread
    suspend fun insert(destination: Destination) {
        destinationDao.insert(destination)
    }
    @WorkerThread
    suspend fun delete(destination: Destination) {
        destinationDao.delete(destination)
    }
    @WorkerThread
    suspend fun deleteAll() {
        destinationDao.deleteAll()
    }
}