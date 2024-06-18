package com.example.futbolix.core.data.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ResponsePlayer(

	@field:SerializedName("player")
	val player: List<PlayerItem>
)

@Parcelize
data class PlayerItem(

	@field:SerializedName("strPlayer")
	val strPlayer: String,

	@field:SerializedName("strPosition")
	val strPosition: String,

	@field:SerializedName("idPlayer")
	val idPlayer: String,

	@field:SerializedName("strNationality")
	val strNationality: String,

	@field:SerializedName("strDescriptionEN")
	val strDescriptionEN: String,

	@field:SerializedName("strNumber")
	val strNumber: String,

	@field:SerializedName("strTeam")
	val strTeam: String,

	@field:SerializedName("strThumb")
	val strThumb: String,

) : Parcelable
