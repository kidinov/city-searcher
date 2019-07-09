package com.kidinov.citysearcher.feature.splash.model

import com.kidinov.citysearcher.common.data.CitiesKeeper
import com.kidinov.citysearcher.common.data.CitiesKeeperImpl
import com.kidinov.citysearcher.feature.splash.SplashContract

class SplashRepo(private val citiesKeeper: CitiesKeeper) : SplashContract.Repo {
    override suspend fun readDataFromJson() {
        citiesKeeper.initCities()
    }
}