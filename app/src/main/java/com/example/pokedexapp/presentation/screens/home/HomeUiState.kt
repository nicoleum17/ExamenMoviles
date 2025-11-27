package com.example.pokedexapp.presentation.screens.home

import com.example.pokedexapp.domain.model.Pokemon

data class HomeUiState(
    val pokemonList: List<Pokemon> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)
