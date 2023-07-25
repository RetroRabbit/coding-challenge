package com.example.gameofthrones.ui.housedetails


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.gameofthrones.data.entities.HouseGameOfThrones
import com.example.gameofthrones.data.repository.HouseRepository
import com.example.gameofthrones.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HouseDetailsViewModel  @Inject constructor(
    private val repository: HouseRepository
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _house = _id.switchMap { id ->
        repository.getHouse(id)
    }
    val houses: LiveData<Resource<HouseGameOfThrones>> = _house


    fun start(id: Int) {
        _id.value = id
    }

}