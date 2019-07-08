package com.kidinov.citysearcher.feature.splash.presenter

import com.kidinov.citysearcher.common.async.DispatcherProvider
import com.kidinov.citysearcher.feature.splash.SplashContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class SplashPresenter(
    private val view: SplashContract.View,
    private val repo: SplashContract.Repo,
    dispatcherProvider: DispatcherProvider
) : SplashContract.Presenter {
    private val supervisorJob = SupervisorJob()
    private val ioScope = CoroutineScope(dispatcherProvider.io + supervisorJob)
    private val uiScope = CoroutineScope(dispatcherProvider.main + supervisorJob)

    override fun start() {
        ioScope.launch {
            repo.readDataFromJson()
            uiScope.launch { view.showNextScreen() }
        }
    }

    override fun stop() {
        supervisorJob.cancel()
    }
}