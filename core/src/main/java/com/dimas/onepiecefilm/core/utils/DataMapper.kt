package com.dimas.onepiecefilm.core.utils

import com.dimas.onepiecefilm.core.source.local.entity.OnePieceEntity
import com.dimas.onepiecefilm.core.source.remote.response.OnePieceResultResponse
import com.dimas.onepiecefilm.core.domain.model.OnePiece


object DataMapper {
    fun mapResponsesToEntities(input: List<OnePieceResultResponse>): List<OnePieceEntity> {
        val itemList = ArrayList<OnePieceEntity>()
        input.map {
          val item =   OnePieceEntity(
                airing = it.airing,
                episodes = it.episodes,
                image_url = it.image_url,
                mal_id = it.mal_id,
                members = it.members,
                rated = it.rated,
                score = it.score,
                start_date = it.start_date,
                synopsis = it.synopsis,
                title = it.title,
                type = it.type,
                isFavorite = false

          )
            itemList.add(item)
        }
        return itemList
    }

    fun mapEntitiesToDomain(input: List<OnePieceEntity>): List<OnePiece> =
        input.map {
            OnePiece(
                airing = it.airing,
                episodes = it.episodes,
                image_url = it.image_url,
                mal_id = it.mal_id,
                members = it.members,
                rated = it.rated,
                score = it.score,
                start_date = it.start_date,
                synopsis = it.synopsis,
                title = it.title,
                type = it.type,
                isFavorite = it.isFavorite
            )
        }
    fun mapDomainToEntity(input: OnePiece) = OnePieceEntity(
        airing = input.airing,
        episodes = input.episodes,
        image_url = input.image_url,
        mal_id = input.mal_id,
        members = input.members,
        rated = input.rated,
        score = input.score,
        start_date = input.start_date,
        synopsis = input.synopsis,
        title = input.title,
        type = input.type,
        isFavorite = input.isFavorite

    )
}