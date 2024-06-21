package com.example.futbolix.ui.detail

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.futbolix.R
import com.example.futbolix.core.data.network.response.PlayerItem
import com.example.futbolix.core.utils.DataMapper
import com.example.futbolix.databinding.ActivityPlayerDetailBinding
import com.example.futbolix.ui.factory.ViewModelFactory
import com.example.futbolix.ui.favorite.FavoriteViewModel

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
            intent.getParcelableExtra(EXTRA_DATA, PlayerItem::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DATA)
        }

        setData(player)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

        viewModel.getFavoritePlayerByUsername(player?.strPlayer ?: "").observe(this) {
            if(it == null) {
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
                    viewModel.delete(DataMapper.mapItemToEntity(player))
                }
            } else {
                if(player != null) {
                    viewModel.insert(DataMapper.mapItemToEntity(player))
                }
            }
        }

    }

    companion object {
        const val EXTRA_DATA = "DATA"
    }

    private fun setData(player: PlayerItem?) {
        if(player != null) {
            with(binding) {
                Glide.with(playerImage)
                    .load(player.strThumb)
                    .circleCrop()
                    .into(playerImage)
                playerTeam.text = player.strTeam
                playerName.text = player.strPlayer
                playerNationality.text = player.strNationality
                playerDescription.text = player.strDescriptionEN
            }
        }
    }
}