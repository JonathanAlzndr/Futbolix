package com.example.futbolix.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.futbolix.core.utils.DataMapper
import com.example.futbolix.databinding.FragmentFavoriteBinding
import com.example.futbolix.ui.factory.PlayerAdapter
import com.example.futbolix.ui.factory.ViewModelFactory

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private lateinit var adapter: PlayerAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

        binding.rvFavorite.layoutManager = LinearLayoutManager(requireActivity())
        adapter = PlayerAdapter()
        binding.rvFavorite.adapter = adapter

        viewModel.getAllFavoritePlayer().observe(viewLifecycleOwner) {
            if(it != null) {
                val playerItems = it.map { player ->
                    DataMapper.mapEntityToItem(player)
                }
                adapter.submitList(playerItems)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}