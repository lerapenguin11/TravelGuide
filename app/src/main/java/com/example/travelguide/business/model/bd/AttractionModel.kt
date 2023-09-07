package com.example.travelguide.business.model.bd

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="attraction_table")
data class AttractionModel(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val nameAttraction : Int,
    val locationAttach : Int,
    val rating : Float,
    val nameContinent : String,
    val description : Int,
    val tag : Int,
    val icon : String,
    var isFavorite: Boolean = false
)