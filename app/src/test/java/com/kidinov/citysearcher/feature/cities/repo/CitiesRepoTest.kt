package com.kidinov.citysearcher.feature.cities.repo

import com.kidinov.citysearcher.common.data.CitiesKeeper
import com.kidinov.citysearcher.common.model.City
import com.kidinov.citysearcher.common.model.Coordinates
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqual
import org.junit.Test

class CitiesRepoTest {
    private val citiesKeeper: CitiesKeeper = mock()
    private val repo = CitiesRepo(citiesKeeper)

    companion object {
        private val testDataCities = mutableListOf(
            City(1, "name2", "country1", Coordinates(0.1, 0.1)),
            City(2, "name1", "country2", Coordinates(0.2, 0.2))
        )
    }

    @Test
    fun `perform filtering Should return data from citiesKeeper`() {
        // GIVEN
        val prefix = "pref"
        whenever(citiesKeeper.findCities(prefix)).thenReturn(testDataCities)

        // WHEN && THEN
        runBlocking { repo.performFiltering(prefix) } shouldEqual testDataCities
    }
}