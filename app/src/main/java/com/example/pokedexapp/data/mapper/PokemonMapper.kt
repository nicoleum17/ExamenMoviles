package com.example.pokedexapp.data.mapper

import com.example.pokedexapp.data.remote.dto.PokemonDto
import com.example.pokedexapp.domain.model.Pokemon

fun PokemonDto.toDomain(): Pokemon =
    Pokemon(
        id = id.toString(),
        name = name.replaceFirstChar { it.uppercase() },
        imageUrl = sprites.frontDefault,
        weight = weight,
        height = height,
        types = types.map { it.type.name },
    )
