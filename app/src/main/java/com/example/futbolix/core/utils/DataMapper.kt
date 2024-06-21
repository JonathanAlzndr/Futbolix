package com.example.futbolix.core.utils

import com.example.futbolix.core.data.local.PlayerEntity
import com.example.futbolix.core.data.network.response.PlayerItem

object DataMapper {
    fun mapEntityToItem(entity: PlayerEntity): PlayerItem {
        return PlayerItem(
            strPlayer = entity.name ?: "",
            strPosition = entity.position ?: "",
            idPlayer = entity.id.toString(),
            strNationality = entity.nationality ?: "",
            strDescriptionEN = entity.description ?: "",
            strTeam = entity.team ?: "",
            strThumb = entity.thumbnail ?: ""
        )
    }
    fun mapItemToEntity(item: PlayerItem): PlayerEntity {
        return PlayerEntity(
            id = item.idPlayer?.toIntOrNull() ?: 0,
            thumbnail = item.strThumb,
            name = item.strPlayer,
            team = item.strTeam,
            description = item.strDescriptionEN,
            position = item.strPosition,
            nationality = item.strNationality
        )
    }

}