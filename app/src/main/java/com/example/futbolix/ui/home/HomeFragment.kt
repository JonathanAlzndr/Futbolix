package com.example.futbolix.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.futbolix.core.data.network.response.PlayerItem
import com.example.futbolix.core.utils.Result
import com.example.futbolix.databinding.FragmentHomeBinding
import com.example.futbolix.ui.PlayerAdapter
import com.example.futbolix.ui.factory.ViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

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

        val factory = ViewModelFactory.getInstance()
        val homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        binding.rvItems.layoutManager = LinearLayoutManager(requireActivity())

        binding.progressBar.visibility = View.INVISIBLE
        binding.tvSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val searchQuery = s?.toString()
                if (searchQuery != null) {
                    homeViewModel.searchPlayer(searchQuery).observe(requireActivity()) {
                        when (it) {
                            is Result.Error -> {
                                showLoading(false)
                                showToast(it.error)
                            }
                            Result.Loading -> showLoading(true)
                            is Result.Success -> {
                                setAdapter(it.data)
                            }
                        }
                    }
                }
            }
        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) binding.progressBar.visibility = View.INVISIBLE
        else binding.progressBar.visibility = View.VISIBLE
    }

    private fun setAdapter(playerList: List<PlayerItem>) {
        binding.rvItems.adapter = PlayerAdapter(playerList)
    }

    private fun showToast(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }
}