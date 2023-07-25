package com.example.gameofthrones.data.repository

import com.example.gameofthrones.data.local.HouseGameOfThronesDao
import com.example.gameofthrones.data.remote.HouseRemoteDataSource
import com.example.gameofthrones.util.performGetOperation
import javax.inject.Inject

class HouseRepository @Inject
constructor(
  private val remoteDataSource: HouseRemoteDataSource,
   private val localDataSource: HouseGameOfThronesDao
) {

    fun getHouse(id: Int) = performGetOperation(
        databaseQuery = { localDataSource.getHouse(id) },
        networkCall = { remoteDataSource.getHouses(id) },
        saveCallResult = { localDataSource.insert(it) }
    )

    fun getHouses() = performGetOperation(
        databaseQuery = { localDataSource.getAllHouses() },
        networkCall = { remoteDataSource.getHouses() },
        saveCallResult = { localDataSource.insertAll(it) }
    )
}
