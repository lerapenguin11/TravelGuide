package com.example.travelguide.business.model.bd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AttractionModel::class], version = 1)
abstract class AttractionDatabase : RoomDatabase() {
    abstract fun sightseeingDao(): AttractionDao

    companion object {
        private var instance: AttractionDatabase? = null

        fun getInstance(context: Context): AttractionDatabase {
            if (instance == null) {
                synchronized(AttractionDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AttractionDatabase::class.java, "attraction_database"
                    ).build()
                }
            }
            return instance!!
        }
    }
}