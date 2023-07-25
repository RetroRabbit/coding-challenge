package com.example.gameofthrones.api

import com.example.gameofthrones.data.GameOfThronesResponsesItem
import retrofit2.Call
import retrofit2.http.GET

interface GameOfThronesApi {
   @GET("api/houses")
fun getHouses(  ):Call<List<GameOfThronesResponsesItem>>


}