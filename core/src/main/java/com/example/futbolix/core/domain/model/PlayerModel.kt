package com.example.futbolix.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlayerModel(
    val id: Int? = 0,
    val name: String? = "",
    val position: String? = "",
    val nationality: String? = "",
    val description: String? = "",
    val team: String? = "",
    val thumbnail: String? = ""
) : Parcelable