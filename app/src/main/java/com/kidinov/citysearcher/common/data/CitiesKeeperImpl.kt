package com.kidinov.citysearcher.common.data

import com.kidinov.citysearcher.common.model.City

interface CitiesKeeper {
    fun initCities()
    fun findCities(prefix: String): List<City>
}

class CitiesKeeperImpl(
    private val jsonFileReader: JsonFileReader,
    private val trie: SearchTrie<City>
) : CitiesKeeper {
    private lateinit var cities: MutableList<City>

    override fun initCities() {
        if (::cities.isInitialized.not()) {
            cities = jsonFileReader.readJson()
            cities.sortWith(Comparator { o1, o2 -> o1.name.compareTo(o2.name) })
            cities.forEach { trie.insert(it.name, it) }
        }
    }

    override fun findCities(prefix: String): List<City> {
        if (prefix.isEmpty()) return cities

        val search = trie.search(prefix)
        search.sortWith(Comparator { o1, o2 -> o1.name.compareTo(o2.name) })
        return search
    }
}