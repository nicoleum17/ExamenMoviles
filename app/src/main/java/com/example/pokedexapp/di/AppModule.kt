package com.example.pokedexapp.di

import com.example.pokedexapp.data.remote.api.PokemonApi
import com.example.pokedexapp.data.repository.PokemonRepositoryImpl
import com.example.pokedexapp.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit
            .Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    // Aquí va la Api de Pokemon
    @Provides
    @Singleton
    fun providePokemonApi(retrofit: Retrofit): PokemonApi = retrofit.create(PokemonApi::class.java)

    // Acá se instancia el repositorio
    @Provides
    @Singleton
    fun providePokemonRepository(api: PokemonApi): PokemonRepository = PokemonRepositoryImpl(api)
}
