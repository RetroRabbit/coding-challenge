package com.example.gameofthrones.ui.house

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gameofthrones.R
import androidx.navigation.fragment.findNavController
import com.example.gameofthrones.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import com.example.gameofthrones.databinding.FragmentHouseBinding
import androidx.lifecycle.Observer
import com.example.gameofthrones.util.Resource


@AndroidEntryPoint
class HouseFragment : Fragment(), HouseAdapter.HouseItemListener {

    private var binding: FragmentHouseBinding  by autoCleared()
    private val viewModel: HouseViewModel by viewModels()
    private lateinit var adapter: HouseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHouseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = HouseAdapter(this)
        binding.housesRv.layoutManager = LinearLayoutManager(requireContext())
        binding.housesRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.houses.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }


    override fun onClickedHouse(houseId: Int) {
        findNavController().navigate(
            R.id.action_houseFragment_to_houseDetailFragment,
            bundleOf("id" to houseId)
        )
    }
}