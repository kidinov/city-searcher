package com.kidinov.citysearcher.feature.splash.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kidinov.citysearcher.R
import com.kidinov.citysearcher.feature.cities.view.CitiesActivity
import com.kidinov.citysearcher.feature.splash.SplashContract
import org.koin.android.scope.currentScope
import org.koin.core.parameter.parametersOf

class SplashActivity : AppCompatActivity(), SplashContract.View {
    private val presenter: SplashContract.Presenter by currentScope.inject {
        parametersOf(this@SplashActivity as SplashContract.View)
    }

    override fun showNextScreen() {
        with(Intent(this, CitiesActivity::class.java)) {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        presenter.start()
    }

    override fun onDestroy() {
        presenter.stop()
        super.onDestroy()
    }
}