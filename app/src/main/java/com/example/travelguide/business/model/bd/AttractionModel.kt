package com.example.travelguide.business.model.bd

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="attraction_table")
data class AttractionModel(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val nameAttraction : String,
    val locationAttach : String,
    val rating : Float,
    val nameContinent : String,
    var isFavorite: Boolean = false
)