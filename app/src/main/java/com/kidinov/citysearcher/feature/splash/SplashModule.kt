package com.kidinov.citysearcher.feature.splash

import com.kidinov.citysearcher.feature.splash.model.SplashRepo
import com.kidinov.citysearcher.feature.splash.presenter.SplashPresenter
import com.kidinov.citysearcher.feature.splash.view.SplashActivity
import org.koin.core.qualifier.named
import org.koin.dsl.module

val splashModule = module {
    scope(named<SplashActivity>()) {
        scoped<SplashContract.Presenter> { (view: SplashContract.View) -> SplashPresenter(view, get()) }
        scoped<SplashContract.Repo> { SplashRepo(get()) }
    }
}