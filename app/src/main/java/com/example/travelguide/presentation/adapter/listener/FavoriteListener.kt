package com.example.travelguide.presentation.adapter.listener

import com.example.travelguide.business.model.bd.AttractionModel

interface FavoriteListener {

    fun favoriteList(attFavoriteList : AttractionModel)
}