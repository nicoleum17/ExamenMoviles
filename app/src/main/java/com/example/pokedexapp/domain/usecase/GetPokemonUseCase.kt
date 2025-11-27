package com.example.pokedexapp.domain.usecase

import com.example.pokedexapp.domain.common.Result
import com.example.pokedexapp.domain.model.Pokemon
import com.example.pokedexapp.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPokemonUseCase
    @Inject
    constructor(
        private val repository: PokemonRepository,
    ) {
        operator fun invoke(id: String): Flow<Result<Pokemon>> =
            flow {
                try {
                    emit(Result.Loading)
                    val pokemon = repository.getPokemonById(id)
                    emit(Result.Success(pokemon))
                } catch (e: Exception) {
                    emit(Result.Error(exception = e))
                }
            }
    }
