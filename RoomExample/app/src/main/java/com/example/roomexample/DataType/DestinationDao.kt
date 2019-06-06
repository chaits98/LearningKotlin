package com.example.roomexample.DataType

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DestinationDao {

    @Query("SELECT * from destinations_table ORDER BY placeName asc")
    fun getAllDestinations(): LiveData<List<Destination>>

    @Insert
    suspend fun insert(destination: Destination)

    @Query("DELETE from destinations_table")
    fun deleteAll()

    @Delete
    fun delete(destination: Destination)
}