package com.example.futbolix.core.utils

import com.example.futbolix.core.data.local.PlayerEntity
import com.example.futbolix.core.data.network.response.PlayerItem
import com.example.futbolix.core.domain.model.PlayerModel

object DataMapper {
    fun mapEntityToResponse(entity: PlayerEntity): PlayerItem {
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

    fun mapResponseToEntity(item: PlayerItem): PlayerEntity {
        return PlayerEntity(
            name = item.strPlayer,
            id = item.idPlayer?.toIntOrNull() ?: 0,
            thumbnail = item.strThumb,
            team = item.strTeam,
            description = item.strDescriptionEN,
            position = item.strPosition,
            nationality = item.strNationality
        )
    }

    fun mapEntitiesToDomain(input: List<PlayerEntity>): List<PlayerModel> {
        return input.map {
            PlayerModel(
                name = it.name,
                id = it.id.toString(),
                thumbnail = it.thumbnail,
                description = it.description,
                position = it.position,
                nationality = it.nationality,
                team = it.team
            )
        }
    }

    fun mapEntityToDomain(input: PlayerEntity) = PlayerModel(
        name = input.name,
        id = input.id.toString(),
        thumbnail = input.thumbnail,
        description = input.description,
        position = input.position,
        nationality = input.nationality,
        team = input.team
    )

    fun mapDomainToEntity(input: PlayerModel) = PlayerEntity(
        name = input.name,
        id = input.id?.toInt() ?: 0,
        thumbnail = input.thumbnail,
        description = input.description,
        position = input.position,
        nationality = input.nationality,
        team = input.team
        )

}