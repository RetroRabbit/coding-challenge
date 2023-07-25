package com.example.gameofthrones.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gameofthrones.data.entities.HouseGameOfThrones

@Dao
interface HouseGameOfThronesDao {

    @Query("SELECT * FROM houses")
    fun getAllHouses() : LiveData<List<HouseGameOfThrones>>

    @Query("SELECT * FROM houses WHERE id = :id")
    fun getHouse(id: Int): LiveData<HouseGameOfThrones>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(house: kotlin.collections.List<com.example.gameofthrones.data.entities.HouseGameOfThrones>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(houses: HouseGameOfThrones)

}