package com.example.futbolix.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.futbolix.core.domain.model.PlayerModel
import com.example.futbolix.core.ui.PlayerAdapter
import com.example.futbolix.databinding.FragmentFavoriteBinding
import com.example.futbolix.detail.PlayerDetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private lateinit var adapter: PlayerAdapter
    private val viewModel: FavoriteViewModel by viewModel()

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

        binding.rvFavorite.layoutManager = LinearLayoutManager(requireActivity())
        adapter = PlayerAdapter()
        binding.rvFavorite.adapter = adapter
        adapter.setOnItemClickCallback(object : PlayerAdapter.OnItemClickCallback {
            override fun onItemClicked(player: PlayerModel) {
                showPlayerDetail(player)
            }
        })

        viewModel.getAllFavoritePlayer().observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showPlayerDetail(player: PlayerModel) {
        val intent = Intent(requireActivity(), PlayerDetailActivity::class.java)
        intent.putExtra(PlayerDetailActivity.EXTRA_DATA, player)
        startActivity(intent)
    }
}