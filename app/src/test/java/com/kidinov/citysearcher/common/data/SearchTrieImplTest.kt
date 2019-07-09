package com.kidinov.citysearcher.common.data

import com.kidinov.citysearcher.common.model.City
import com.kidinov.citysearcher.common.model.Coordinates
import org.amshove.kluent.shouldEqual
import org.junit.Test

class SearchTrieImplTest {
    private val trie = SearchTrieImpl<City>()

    @Test
    fun `search With nothing inserted Should return empty list`() {
        //WHEN && THEN
        trie.search("") shouldEqual emptyList()
        trie.search("pref") shouldEqual emptyList()
    }

    @Test
    fun `search With inserted data Should return data filtered by prefix`() {
        // GIVEN
        val cities = ArrayList<City>()
        repeat(10000) {
            val city = City(it.toLong(), it.toString(), "country", Coordinates(0.0, 0.0))
            cities.add(city)
            trie.insert(city.name, city)
        }
        cities.sortBy { it.name }

        //WHEN && THEN
        trie.search("").sortedBy { it.name } shouldEqual cities
        trie.search("1").sortedBy { it.name } shouldEqual cities.filter { it.name.startsWith("1") }
        trie.search("7").sortedBy { it.name } shouldEqual cities.filter { it.name.startsWith("7") }
        trie.search("1000").sortedBy { it.name } shouldEqual cities.filter { it.name.startsWith("1000") }
        trie.search("130000").sortedBy { it.name } shouldEqual emptyList()
        trie.search("a").sortedBy { it.name } shouldEqual emptyList()
    }
}