package com.example.roomexample.DataType

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "destinations_table")
data class Destination (
    @PrimaryKey @ColumnInfo(name = "placeName") val placeName: String,
    @ColumnInfo(name = "cityName") val cityName: String,
    @ColumnInfo(name = "countryName") val countryName: String
)