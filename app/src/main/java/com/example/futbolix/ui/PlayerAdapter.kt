package com.example.futbolix.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.futbolix.R
import com.example.futbolix.core.data.network.response.PlayerItem

class PlayerAdapter(val playerList: List<PlayerItem>) : RecyclerView.Adapter<PlayerAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvPlayerName: TextView = itemView.findViewById(R.id.tv_playerName)
        val tvPlayerPosition: TextView = itemView.findViewById(R.id.tv_playerPosition)
        val tvPlayerNationality: TextView = itemView.findViewById(R.id.tv_playerNationality)
        val tvPlayerClub: TextView = itemView.findViewById(R.id.tv_playerClub)
        val ivPlayerIcon: ImageView = itemView.findViewById(R.id.iv_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.apply {
            tvPlayerClub.text = playerList[position].strTeam
            tvPlayerName.text = playerList[position].strPlayer
            tvPlayerNationality.text = playerList[position].strNationality
            tvPlayerPosition.text = playerList[position].strPosition
            Glide.with(holder.ivPlayerIcon.context)
                .load(playerList[position].strThumb)
                .circleCrop()
                .into(ivPlayerIcon)
        }
    }

    override fun getItemCount(): Int = playerList.size

}