package com.example.pokedexapp.presentation.screens.home

import SearchTab
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pokedexapp.domain.model.Pokemon
import com.example.pokedexapp.presentation.screens.home.components.PokemonCard
import com.example.pokedexapp.presentation.screens.home.components.PokemonListTab

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeScreen(
    onPokemonClick: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Pokémon List", "Search")
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Pokédex") },
            )
        },
    ) { padding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(padding),
        ) {
            TabRow(selectedTabIndex = selectedTabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                    )
                }
            }

            when (selectedTabIndex) {
                0 ->
                    PokemonListContent(
                        pokemonList = uiState.pokemonList,
                        isLoading = uiState.isLoading,
                        error = uiState.error,
                        onPokemonClick = onPokemonClick,
                    )
                1 -> SearchTab(onPokemonClick = onPokemonClick)
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun PokemonListContent(
    pokemonList: List<Pokemon>,
    isLoading: Boolean,
    error: String?,
    onPokemonClick: (String) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                )
            }
            error != null -> {
                Text(
                    text = error,
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.error,
                )
            }
            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(
                        items = pokemonList,
                        key = { it.id },
                    ) { pokemon ->
                        PokemonCard(
                            pokemon = pokemon,
                            onClick = { onPokemonClick(pokemon.id) },
                        )
                    }
                }
            }
        }
    }
}
