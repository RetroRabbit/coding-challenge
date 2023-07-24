package com.example.gameofthrones.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.TextView
import com.example.gameofthrones.R
import com.example.gameofthrones.data.GameOfThronesResponsesItem

class GameOfThronesAdapter(private val houses: List<GameOfThronesResponsesItem>) : RecyclerView.Adapter<GameOfThronesAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_game_of_thrones, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(houses[position])

    override fun getItemCount() = houses.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
              fun bind(item: GameOfThronesResponsesItem) {
            val textView: TextView = itemView.findViewById(R.id.tv_name)
            textView.text = item.name
        }
    }

}