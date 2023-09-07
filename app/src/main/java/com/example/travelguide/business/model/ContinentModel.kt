package com.example.travelguide.business.model

import com.example.travelguide.business.model.bd.AttractionModel

data class ContinentModel(
    val id : Int,
    val nameContinent : Int,
    val attractionModels: List<AttractionModel>
)
