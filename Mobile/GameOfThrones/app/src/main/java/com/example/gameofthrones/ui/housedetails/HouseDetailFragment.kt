package com.example.gameofthrones.ui.housedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.gameofthrones.R
import com.example.gameofthrones.data.entities.HouseGameOfThrones
import com.example.gameofthrones.databinding.FragmentHouseDetailBinding
import com.example.gameofthrones.util.Resource
import com.example.gameofthrones.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HouseDetailFragment : Fragment(){

    private var binding: FragmentHouseDetailBinding by autoCleared()
    private val viewModel: HouseDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHouseDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { viewModel.start(it) }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.houses.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    bindHouse(it.data!!)
                    binding.progressBar.visibility = View.GONE
                    binding.houseCl.visibility = View.VISIBLE
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.houseCl.visibility = View.GONE
                }
            }
        })
    }

    private fun bindHouse(houseDetail: HouseGameOfThrones) {
        binding.name.text = houseDetail.name
        binding.coatOfArms.text = houseDetail.coatOfArms
        binding.currentLord.text = houseDetail.currentLord
        binding.founded.text = houseDetail.founded
        binding.founder.text = houseDetail.founder
        binding.heir.text = houseDetail.heir
        binding.diedOut.text = houseDetail.diedOut
        binding.overlord.text = houseDetail.overlord
        binding.region.text = houseDetail.region
        binding.words.text = houseDetail.words
        Glide.with(binding.root)
            .load(houseDetail.url)
            .transform(CircleCrop())
            .into(binding.image)
    }
}