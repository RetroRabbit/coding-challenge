package com.example.gameofthrones.data.remote

import javax.inject.Inject

class HouseRemoteDataSource @Inject constructor(
    private val houseService: HouseService
): BaseDataSource() {

    suspend fun getHouses() = getResult { houseService.getAllHouses() }
    suspend fun getHouses(id: Int) = getResult { houseService.getHouse(id) }
}