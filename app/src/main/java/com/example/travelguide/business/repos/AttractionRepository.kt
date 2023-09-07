package com.example.travelguide.business.repos

import androidx.lifecycle.LiveData
import com.example.travelguide.business.model.bd.AttractionDao
import com.example.travelguide.business.model.bd.AttractionModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AttractionRepository (private val attractionDao: AttractionDao) {

    val allSightseeing: LiveData<List<AttractionModel>> = attractionDao.getAll()
    val favoriteAttractions: LiveData<List<AttractionModel>> = attractionDao.getFavoriteAttractions()

    suspend fun insert(sightseeing: AttractionModel) {
        withContext(Dispatchers.IO) {
            attractionDao.insert(sightseeing)
        }
    }

    suspend fun update(sightseeing: AttractionModel) {
        withContext(Dispatchers.IO) {
            attractionDao.update(sightseeing)
        }
    }

    suspend fun delete(sightseeing: AttractionModel) {
        withContext(Dispatchers.IO) {
            attractionDao.delete(sightseeing)
        }
    }
}