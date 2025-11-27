package com.example.pokedexapp.data.repository

import com.example.pokedexapp.data.mapper.toDomain
import com.example.pokedexapp.data.remote.api.PokemonApi
import com.example.pokedexapp.domain.model.Pokemon
import com.example.pokedexapp.domain.repository.PokemonRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepositoryImpl
    @Inject
    constructor(
        private val api: PokemonApi,
    ) : PokemonRepository {
        override suspend fun getPokemonList(): List<Pokemon> {
            val response = api.getPokemonList()
            return response.results.map { result ->
                // Obtenemos el id de la URL
                val id =
                    result.url
                        .split("/")
                        .dropLast(1)
                        .last()
                // Hacemos la llamada para obtener detalles
                api.getPokemon(id).toDomain()
            }
        }

        override suspend fun getPokemonById(id: String): Pokemon = api.getPokemon(id).toDomain()
    }
