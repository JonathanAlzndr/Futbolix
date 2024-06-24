package com.example.futbolix.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.futbolix.core.domain.model.PlayerModel
import com.example.futbolix.core.ui.PlayerAdapter
import com.example.futbolix.core.utils.Result
import com.example.futbolix.databinding.FragmentHomeBinding
import com.example.futbolix.detail.PlayerDetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel: HomeViewModel by viewModel()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var adapter: PlayerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.rvItems.layoutManager = LinearLayoutManager(requireActivity())
        adapter = PlayerAdapter()
        binding.rvItems.adapter = adapter
        adapter.setOnItemClickCallback(object : PlayerAdapter.OnItemClickCallback {
            override fun onItemClicked(player: PlayerModel) {
                showPlayerDetail(player)
            }
        })
        showLoading(false)
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    homeViewModel.searchPlayer(query).observe(viewLifecycleOwner) {
                        when (it) {
                            is Result.Error -> {
                                showLoading(false)
                                showToast(it.error)
                            }

                            Result.Loading -> showLoading(true)
                            is Result.Success -> {
                                showLoading(false)
                                adapter.submitList(it.data)
                            }
                        }
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    private fun showPlayerDetail(player: PlayerModel) {
        val intent = Intent(requireActivity(), PlayerDetailActivity::class.java)
        intent.putExtra(PlayerDetailActivity.EXTRA_DATA, player)
        startActivity(intent)
    }
}