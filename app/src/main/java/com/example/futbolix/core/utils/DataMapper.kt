package com.example.futbolix.core.utils

import com.example.futbolix.core.data.local.PlayerEntity
import com.example.futbolix.core.data.network.response.PlayerItem
import com.example.futbolix.core.domain.model.PlayerModel

object DataMapper {

    fun mapEntitiesToDomain(input: List<PlayerEntity>): List<PlayerModel> {
        return input.map {
            PlayerModel(
                name = it.name,
                id = it.id,
                thumbnail = it.thumbnail,
                description = it.description,
                position = it.position,
                nationality = it.nationality,
                team = it.team
            )
        }
    }

    fun mapEntityToDomain(input: PlayerEntity) =
        if (input != null) {
            PlayerModel(
                name = input.name ?: "",
                id = input.id,
                thumbnail = input.thumbnail ?: "",
                description = input.description ?: "",
                position = input.position ?: "",
                nationality = input.nationality ?: "",
                team = input.team ?: ""
            )
        }
        else {
            PlayerModel()
        }

    fun mapDomainToEntity(input: PlayerModel) = PlayerEntity(
        name = input.name ?: "",
        id = input.id ?: 0,
        thumbnail = input.thumbnail ?: "",
        description = input.description ?: "",
        position = input.position ?: "",
        nationality = input.nationality ?: "",
        team = input.team ?: ""
    )

    fun mapPlayerItemToDomain(input: List<PlayerItem>): List<PlayerModel> {
        return input.map {
            PlayerModel(
                name = it.strPlayer,
                id = it.idPlayer?.toInt(),
                thumbnail = it.strThumb,
                description = it.strDescriptionEN,
                position = it.strPosition,
                nationality = it.strNationality,
                team = it.strTeam
            )
        }
    }
}