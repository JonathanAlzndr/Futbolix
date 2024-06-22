package com.example.futbolix.ui.factory

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.futbolix.R
import com.example.futbolix.core.domain.model.PlayerModel
import com.example.futbolix.ui.detail.PlayerDetailActivity

class PlayerAdapter : ListAdapter<PlayerModel, PlayerAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvPlayerName: TextView = itemView.findViewById(R.id.tv_playerName)
        val tvPlayerNationality: TextView = itemView.findViewById(R.id.tv_playerNationality)
        val ivPlayerIcon: ImageView = itemView.findViewById(R.id.iv_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return MyViewHolder(view)
    }

    @Suppress("DEPRECATION")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val player = getItem(position)
        holder.apply {
            tvPlayerName.text = player.name
            tvPlayerNationality.text = player.nationality
            Glide.with(holder.ivPlayerIcon.context)
                .load(player.thumbnail)
                .circleCrop()
                .into(ivPlayerIcon)
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, PlayerDetailActivity::class.java)
            intent.putExtra(PlayerDetailActivity.EXTRA_DATA, player)
            holder.itemView.context.startActivity(intent)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PlayerModel>() {
            override fun areItemsTheSame(oldItem: PlayerModel, newItem: PlayerModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PlayerModel, newItem: PlayerModel): Boolean {
                return oldItem == newItem
            }
        }
    }

}