package com.kidinov.citysearcher.common.data

import com.kidinov.citysearcher.common.model.City

class CitiesKeeper(
    private val jsonFileReader: JsonFileReader,
    private val trie: SearchTrie<City>
) {
    private lateinit var cities: MutableList<City>

    fun initCities() {
        if (::cities.isInitialized.not()) {
            cities = jsonFileReader.readJson()
            cities.sortWith(Comparator { o1, o2 -> o1.name.compareTo(o2.name) })
            cities.forEach { trie.insert(it.name, it) }
        }
    }

    fun findCities(prefix: String): List<City> {
        if (prefix.isEmpty()) return cities

        val search = trie.search(prefix)
        search.sortWith(Comparator { o1, o2 -> o1.name.compareTo(o2.name) })
        return search
    }
}