package com.example.pokedexapp.domain.repository

import com.example.pokedexapp.domain.model.Pokemon

interface PokemonRepository {
    suspend fun getPokemonList(): List<Pokemon>

    suspend fun getPokemonById(id: String): Pokemon
}
