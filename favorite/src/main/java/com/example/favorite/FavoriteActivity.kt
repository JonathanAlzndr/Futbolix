package com.example.favorite

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.favorite.databinding.ActivityFavoriteBinding
import com.example.futbolix.core.domain.model.PlayerModel
import com.example.futbolix.core.ui.PlayerAdapter
import com.example.futbolix.detail.PlayerDetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var adapter: PlayerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loadKoinModules(favoriteModule)

        supportActionBar?.title = "Favorite Player"
        binding.rvFavorite.layoutManager = LinearLayoutManager(this@FavoriteActivity)
        adapter = PlayerAdapter()
        binding.rvFavorite.adapter = adapter
        adapter.setOnItemClickCallback(object : PlayerAdapter.OnItemClickCallback {
            override fun onItemClicked(player: PlayerModel) {
                showPlayerDetail(player)
            }
        })

        favoriteViewModel.getAllFavoritePlayer().observe(this) {
            if (it != null) {
                adapter.submitList(it)
            }
        }


    }

    private fun showPlayerDetail(player: PlayerModel) {
        val intent = Intent(this@FavoriteActivity, PlayerDetailActivity::class.java)
        intent.putExtra(PlayerDetailActivity.EXTRA_DATA, player)
        startActivity(intent)
    }
}