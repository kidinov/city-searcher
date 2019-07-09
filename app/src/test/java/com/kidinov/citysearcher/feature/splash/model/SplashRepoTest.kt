package com.kidinov.citysearcher.feature.splash.model

import com.kidinov.citysearcher.common.data.CitiesKeeper
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SplashRepoTest {
    private val citiesKeeper: CitiesKeeper = mock()
    private val repo = SplashRepo(citiesKeeper)

    @Test
    fun `readDataFromJson Should call citiesKeeper`() {
        // WHEN
        runBlocking { repo.readDataFromJson() }

        // THEN
        verify(citiesKeeper).initCities()
    }
}