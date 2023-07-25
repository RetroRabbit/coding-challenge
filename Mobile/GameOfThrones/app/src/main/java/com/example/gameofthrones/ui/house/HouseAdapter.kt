package com.example.gameofthrones.ui.house

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.gameofthrones.data.entities.HouseGameOfThrones
import com.example.gameofthrones.databinding.ItemHouseBinding


class HouseAdapter(private val listener: HouseItemListener) : RecyclerView.Adapter<HouseViewHolder>() {

    interface HouseItemListener {
        fun onClickedHouse(houseId: Int)
    }
    private val items = ArrayList<HouseGameOfThrones>()

    fun setItems(items: ArrayList<HouseGameOfThrones>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HouseViewHolder {
        val binding: ItemHouseBinding = ItemHouseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HouseViewHolder(binding, listener)
   }
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: HouseViewHolder, position: Int) = holder.bind(items[position])
    }

class HouseViewHolder(private val itemBinding: ItemHouseBinding, private val listener: HouseAdapter.HouseItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var house: HouseGameOfThrones

    init {
        itemBinding.root.setOnClickListener(this)
    }
    @SuppressLint("SetTextI18n")
    fun bind(item: HouseGameOfThrones) {
        this.house = item
        itemBinding.tvName.text = item.name
               Glide.with(itemBinding.root)
            .load(item.overlord)
            .transform(CircleCrop())
            .into(itemBinding.image)
    }
    override fun onClick(v: View?) {
        house.id?.let { listener.onClickedHouse(it) }
       }

}