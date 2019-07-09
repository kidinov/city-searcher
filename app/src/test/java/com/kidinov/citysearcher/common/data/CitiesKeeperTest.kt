package com.kidinov.citysearcher.common.data

import com.kidinov.citysearcher.common.model.City
import com.kidinov.citysearcher.common.model.Coordinates
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.amshove.kluent.shouldEqual
import org.junit.Test

class CitiesKeeperTest {
    private val jsonFileReader: JsonFileReader = mock()
    private val trie: SearchTrie<City> = mock()
    private val keeper = CitiesKeeperImpl(jsonFileReader, trie)

    companion object {
        private val testDataCities = mutableListOf(
            City(1, "name2", "country1", Coordinates(0.1, 0.1)),
            City(2, "name1", "country2", Coordinates(0.2, 0.2))
        )
    }

    @Test
    fun `initCities Should insert in index`() {
        // GIVEN
        whenever(jsonFileReader.readJson()).thenReturn(testDataCities)

        // WHEN
        keeper.initCities()

        // THEN
        testDataCities.forEach { verify(trie).insert(it.name, it) }
    }

    @Test
    fun `findCities With empty query Should return list from jsonFileReader`() {
        // GIVEN
        whenever(jsonFileReader.readJson()).thenReturn(testDataCities)
        keeper.initCities()

        // WHEN
        val result = keeper.findCities("")

        // THEN
        result shouldEqual testDataCities.sortedBy { it.name }
    }

    @Test
    fun `findCities With not query Should return list from trie`() {
        // GIVEN
        val prefix = "prefix"
        whenever(jsonFileReader.readJson()).thenReturn(testDataCities)
        keeper.initCities()
        val filteredResult = mutableListOf(testDataCities[0])
        whenever(trie.search(prefix)).thenReturn(filteredResult)

        // WHEN
        val result = keeper.findCities(prefix)

        // THEN
        result shouldEqual filteredResult
    }
}