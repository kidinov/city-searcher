package com.kidinov.citysearcher.feature.cities.repo

import com.kidinov.citysearcher.common.data.CitiesKeeper
import com.kidinov.citysearcher.feature.cities.CitiesContract

class CitiesRepo(private val citiesKeeper: CitiesKeeper) : CitiesContract.Repo {
    override suspend fun performFiltering(prefix: String) = citiesKeeper.findCities(prefix)
}