package com.example.travelguide.business.model

data class ContinentModel(
    val id : Int,
    val nameContinent : String,
    val attractionModels: List<AttractionModel>
)
