package com.example.gameofthrones.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gameofthrones.R
import com.example.gameofthrones.data.GameOfThronesResponsesItem

class HouseDetailsActivity : AppCompatActivity() {

    companion object {
        private val TAG = HouseDetailsActivity::class.java.simpleName
        const val HOUSE = "house"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_house_details)

        val house : Parcelable? = intent.getParcelableExtra(HOUSE)

        val textView = findViewById<TextView>(R.id.tvurl)
        textView.text = house?.toString()

    }
}