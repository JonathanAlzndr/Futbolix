package com.example.futbolix.core.data.network.response

import com.google.gson.annotations.SerializedName

data class ResponsePlayer(

	@field:SerializedName("player")
	val player: List<PlayerItem>
)

data class PlayerItem(

	@field:SerializedName("strPlayer")
	val strPlayer: String,

	@field:SerializedName("strWeight")
	val strWeight: String,

	@field:SerializedName("strBirthLocation")
	val strBirthLocation: String,

	@field:SerializedName("strPosition")
	val strPosition: String,

	@field:SerializedName("idPlayer")
	val idPlayer: String,

	@field:SerializedName("strWage")
	val strWage: String,

	@field:SerializedName("dateBorn")
	val dateBorn: String,

	@field:SerializedName("strNationality")
	val strNationality: String,

	@field:SerializedName("strDescriptionEN")
	val strDescriptionEN: String,

	@field:SerializedName("strNumber")
	val strNumber: String,

	@field:SerializedName("strHeight")
	val strHeight: String,

	@field:SerializedName("strTeam")
	val strTeam: String,

	@field:SerializedName("strSigning")
	val strSigning: String,

	@field:SerializedName("strGender")
	val strGender: String,

	@field:SerializedName("strThumb")
	val strThumb: String,

)
