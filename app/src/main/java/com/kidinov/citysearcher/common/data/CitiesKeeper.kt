package com.kidinov.citysearcher.common.data

import com.kidinov.citysearcher.common.model.City

class CitiesKeeper(
    private val jsonFileReader: JsonFileReader,
    private val trie: SearchTrie<City>
) {
    private lateinit var cities: List<City>

    fun initCities() {
        if (::cities.isInitialized.not()) {
            cities = jsonFileReader.readJson()
            cities.forEach { trie.insert(it.name, it) }
        }
    }

    fun findCities(prefix: String): List<City> {
        if (prefix.isEmpty()) return cities

        return trie.search(prefix)
    }
}