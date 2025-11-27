package com.example.pokedexapp.presentation.screens.detail

import com.example.pokedexapp.domain.model.Pokemon

data class PokemonDetailUiState(
    val pokemon: Pokemon? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)
