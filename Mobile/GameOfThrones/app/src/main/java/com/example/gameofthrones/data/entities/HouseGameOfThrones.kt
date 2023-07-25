package com.example.gameofthrones.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "houses")
data class HouseGameOfThrones(
    @TypeConverters(StringListTypeConverter::class)
    val ancestralWeapons: List<String>? = null,
    val cadetBranches: List<String>? = null,
    val coatOfArms: String,
    val currentLord: String,
    val diedOut: String,
    val founded: String,
    val founder: String,
    val heir: String,
    val name: String,
    val overlord: String,
    val region: String,
    val seats: List<String>,
    val swornMembers: List<String>? = null,
    val titles: List<String>? = null,
    val url: String,
    val words: String
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}

