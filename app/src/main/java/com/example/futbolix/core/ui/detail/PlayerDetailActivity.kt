package com.example.futbolix.core.ui.detail

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.futbolix.R
import com.example.futbolix.core.domain.model.PlayerModel
import com.example.futbolix.databinding.ActivityPlayerDetailBinding
import com.example.futbolix.core.ui.factory.ViewModelFactory
import com.example.futbolix.core.ui.favorite.FavoriteViewModel

class PlayerDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerDetailBinding
    private var isFavorite = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPlayerDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val player = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DATA, PlayerModel::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DATA)
        }

        if (player != null) {
            setData(player)
        }

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

        viewModel.getFavoritePlayerByUsername(player?.name ?: "").observe(this) {
            if(it == PlayerModel()) {
                binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
                isFavorite = false
            } else {
                binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_red_24)
                isFavorite = true
            }
        }

        binding.fabFavorite.setOnClickListener {
            if(isFavorite) {
                if(player != null) {
                    viewModel.delete(player)
                }
            } else {
                if(player != null) {
                    viewModel.insert(player)
                }
            }
        }

    }

    companion object {
        const val EXTRA_DATA = "DATA"
    }

    private fun setData(player: PlayerModel) {
        with(binding) {
        Glide.with(playerImage)
            .load(player.thumbnail)
            .circleCrop()
            .into(playerImage)
        playerTeam.text = player.team
        playerName.text = player.name
        playerNationality.text = player.nationality
        playerDescription.text = player.description
    }
    }
}