package com.example.roomexample.DataType

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Destination::class], version = 1)
public abstract class DestinationDatabase: RoomDatabase() {

    abstract fun destinationDao(): DestinationDao

    companion object {
        @Volatile
        private var databaseInstance: DestinationDatabase? = null

        fun getDatabase(context: Context): DestinationDatabase {

            val tempInstance = databaseInstance

            if (tempInstance != null) {
                return tempInstance
            } else {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DestinationDatabase::class.java,
                    "destination_database"
                ).build()

                databaseInstance = instance

                return instance
            }
//
//            synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    DestinationDatabase::class.java,
//                    "destination_database"
//                ).build()
//
//                databaseInstance = instance
//
//                return instance
//            }
        }
    }
}