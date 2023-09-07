package com.example.travelguide.business.model.bd

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AttractionDao {

    @Query("SELECT * FROM attraction_table")
    fun getAll(): LiveData<List<AttractionModel>>

    @Insert
    fun insert(sightseeing: AttractionModel)

    @Update
    fun update(sightseeing: AttractionModel)

    @Delete
    fun delete(sightseeing: AttractionModel)

    @Query("SELECT * FROM attraction_table WHERE isFavorite = 1")
    fun getFavoriteAttractions(): LiveData<List<AttractionModel>>
}