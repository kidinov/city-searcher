package com.kidinov.citysearcher.feature.splash.presenter

import com.kidinov.citysearcher.feature.splash.SplashContract
import com.kidinov.citysearcher.util.CoroutinesRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyBlocking
import org.junit.Rule
import org.junit.Test

class SplashPresenterTest {
    @Rule
    @JvmField
    val test = CoroutinesRule()

    private val view: SplashContract.View = mock()
    private val repo: SplashContract.Repo = mock()
    private val presenter = SplashPresenter(view, repo)

    @Test
    fun `in start Should read data from json and go to next screen`() {
        // WHEN
        presenter.start()
        Thread.sleep(30)

        // THEN
        verifyBlocking(repo) { repo.readDataFromJson() }
        verify(view).showNextScreen()
    }
}