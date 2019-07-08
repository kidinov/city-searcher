package com.kidinov.citysearcher.feature.splash

import com.kidinov.citysearcher.common.base.BasePresenter

interface SplashContract {
    interface View {
        fun showNextScreen()
    }

    interface Presenter : BasePresenter

    interface Repo {
        suspend fun readDataFromJson()
    }
}