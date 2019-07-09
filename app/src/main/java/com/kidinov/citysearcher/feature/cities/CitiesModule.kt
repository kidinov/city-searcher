package com.kidinov.citysearcher.feature.cities

import com.kidinov.citysearcher.feature.cities.presenter.CitiesPresenter
import com.kidinov.citysearcher.feature.cities.repo.CitiesRepo
import com.kidinov.citysearcher.feature.cities.view.CitiesActivity
import com.kidinov.citysearcher.feature.cities.view.CitiesAdapter
import org.koin.core.qualifier.named
import org.koin.dsl.module

val citiesModule = module {
    scope(named<CitiesActivity>()) {
        scoped<CitiesContract.Presenter> { (view: CitiesContract.View) -> CitiesPresenter(view, get()) }
        scoped<CitiesContract.Repo> { CitiesRepo(get()) }
        scoped { CitiesAdapter(get()) }
    }
}