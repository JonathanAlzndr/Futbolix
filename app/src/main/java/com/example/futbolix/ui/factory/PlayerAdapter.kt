package com.example.futbolix.ui.factory

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.futbolix.R
import com.example.futbolix.core.data.network.response.PlayerItem
import com.example.futbolix.ui.PlayerDetailActivity

class PlayerAdapter(val playerList: List<PlayerItem>) : RecyclerView.Adapter<PlayerAdapter.MyViewHolder>() {
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
        holder.apply {
            tvPlayerName.text = playerList[position].strPlayer
            tvPlayerNationality.text = playerList[position].strNationality
            Glide.with(holder.ivPlayerIcon.context)
                .load(playerList[position].strThumb)
                .circleCrop()
                .into(ivPlayerIcon)
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, PlayerDetailActivity::class.java)
            intent.putExtra(PlayerDetailActivity.EXTRA_DATA, playerList[holder.position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = playerList.size

}