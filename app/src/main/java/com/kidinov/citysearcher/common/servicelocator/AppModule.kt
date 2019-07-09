package com.kidinov.citysearcher.common.servicelocator

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kidinov.citysearcher.common.data.CitiesKeeper
import com.kidinov.citysearcher.common.data.CitiesKeeperImpl
import com.kidinov.citysearcher.common.data.JsonFileReader
import com.kidinov.citysearcher.common.data.JsonFileReaderImpl
import com.kidinov.citysearcher.common.data.SearchTrie
import com.kidinov.citysearcher.common.data.SearchTrieImpl
import com.kidinov.citysearcher.common.misc.Config
import com.kidinov.citysearcher.common.model.City
import org.koin.dsl.module

val appModule = module {
    single<Gson> { GsonBuilder().create() }
    single<JsonFileReader> { JsonFileReaderImpl(get(), get(), get()) }
    single<CitiesKeeper> { CitiesKeeperImpl(get(), get()) }
    single { Config() }
    single<SearchTrie<City>> { SearchTrieImpl() }
}