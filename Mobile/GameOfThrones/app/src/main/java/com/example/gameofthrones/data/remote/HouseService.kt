package com.example.gameofthrones.data.remote

import com.example.gameofthrones.data.entities.HouseGameOfThrones
import com.example.gameofthrones.data.entities.HouseList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HouseService {
    @GET("houses")
    suspend fun getAllHouses() : Response<List<HouseGameOfThrones>>

    @GET("houses/{id}")
    suspend fun getHouse(@Path("id") id: Int): Response<HouseGameOfThrones>
}