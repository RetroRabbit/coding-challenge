package com.example.gameofthrones.ui

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gameofthrones.R
import com.example.gameofthrones.adapters.GameOfThronesAdapter
import com.example.gameofthrones.api.GameOfThronesApi
import com.example.gameofthrones.api.RetrofitInstance
import com.example.gameofthrones.data.GameOfThronesResponsesItem


class MainActivity : AppCompatActivity() {

    var retrofit = RetrofitInstance.getInstance()
    var apiInterface = retrofit.create(GameOfThronesApi::class.java)
    private var apiService: GameOfThronesApi? = null

    private var adapter: GameOfThronesAdapter? = null
    private var houses: MutableList<GameOfThronesResponsesItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        var retrofit = RetrofitInstance.getInstance()
        var apiInterface = retrofit.create(GameOfThronesApi::class.java)

        recyclerview.layoutManager = LinearLayoutManager(this)

        adapter = GameOfThronesAdapter(houses)
        recyclerview!!.adapter = adapter



        fetchCharactersList()

    }

    private fun fetchCharactersList() {
        //val service: GameOfThronesApi = retrofit.create(ApiClient::class.java)
        apiInterface.getHouses()?.enqueue(object : retrofit2.Callback<List<GameOfThronesResponsesItem>> {
            override fun onResponse(
                call: retrofit2.Call<List<GameOfThronesResponsesItem>>,
                response: retrofit2.Response<List<GameOfThronesResponsesItem>>
            ) {
                val houses = response.body()
                if (houses != null) {
                    val adapter = GameOfThronesAdapter(houses)
                    val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
                    recyclerview.adapter = adapter
                    adapter!!.notifyDataSetChanged()
                }


            }

            override fun onFailure(
                call: retrofit2.Call<List<GameOfThronesResponsesItem>>,
                t: Throwable
            ) {
                Log.e(TAG, "Got error : " + t.localizedMessage)
            }
        })
    }
}

