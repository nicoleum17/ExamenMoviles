package com.example.pokedexapp.data.remote.api

import com.example.pokedexapp.data.remote.dto.PokemonDto
import com.example.pokedexapp.data.remote.dto.PokemonListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 151,
        @Query("offset") offset: Int = 0,
    ): PokemonListDto

    @GET("pokemon/{id}")
    suspend fun getPokemon(
        @Path("id") id: String,
    ): PokemonDto
}
