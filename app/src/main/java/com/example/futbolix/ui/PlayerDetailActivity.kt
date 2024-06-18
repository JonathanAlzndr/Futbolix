package com.example.futbolix.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.futbolix.R
import com.example.futbolix.core.data.network.response.PlayerItem
import com.example.futbolix.databinding.ActivityPlayerDetailBinding

class PlayerDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerDetailBinding
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