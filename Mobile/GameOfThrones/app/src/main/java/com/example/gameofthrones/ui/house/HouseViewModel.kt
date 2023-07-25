package com.example.gameofthrones.ui.house


import androidx.lifecycle.ViewModel
import com.example.gameofthrones.data.repository.HouseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HouseViewModel  @Inject constructor(
    private val repository: HouseRepository
) : ViewModel() {

    val houses = repository.getHouses()
}