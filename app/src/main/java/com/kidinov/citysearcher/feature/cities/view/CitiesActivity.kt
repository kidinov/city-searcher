package com.kidinov.citysearcher.feature.cities.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kidinov.citysearcher.R
import com.kidinov.citysearcher.common.misc.TextWatcherAdapter
import com.kidinov.citysearcher.common.model.Coordinates
import com.kidinov.citysearcher.feature.cities.CitiesContract
import kotlinx.android.synthetic.main.activity_cities.etFilter
import kotlinx.android.synthetic.main.activity_cities.rvCities
import org.koin.android.scope.currentScope
import org.koin.core.parameter.parametersOf


class CitiesActivity : AppCompatActivity(), CitiesContract.View {
    private val presenter: CitiesContract.Presenter by currentScope.inject {
        parametersOf(this@CitiesActivity as CitiesContract.View)
    }
    private val citiesAdapter: CitiesAdapter by currentScope.inject()

    private val filterListener by lazy {
        object : TextWatcherAdapter {
            override fun afterTextChanged(s: Editable) {
                presenter.onPrefixChanged(s.toString())
            }
        }
    }

    override fun updateList() {
        citiesAdapter.notifyDataSetChanged()
    }

    override fun showEmptyState() {
        Toast.makeText(this, R.string.empty_cities_state, Toast.LENGTH_SHORT).show()
    }

    override fun showMapWithCoordinates(coordinates: Coordinates) {
        val gmmIntentUri = Uri.parse("geo:${coordinates.lat},${coordinates.lon}")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        if (mapIntent.resolveActivity(packageManager) != null) {
            startActivity(mapIntent)
        } else {
            Toast.makeText(this, R.string.no_maps_state, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities)

        presenter.start()
        initList()
        initFilter()
        restoreState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.query = etFilter.text?.toString() ?: ""
    }

    override fun onDestroy() {
        presenter.stop()
        etFilter.removeTextChangedListener(filterListener)
        super.onDestroy()
    }

    private fun initList() {
        with(rvCities) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@CitiesActivity)
            citiesAdapter.setHasStableIds(true)
            adapter = citiesAdapter
        }
    }

    private fun initFilter() {
        etFilter.addTextChangedListener(filterListener)
    }

    private fun restoreState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            etFilter.setText(savedInstanceState.query)
        } else {
            presenter.onPrefixChanged("")
        }
    }

    companion object {
        private const val QUERY = "query"
        private var Bundle.query
            get() = getString(QUERY)
            set(value) = putString(QUERY, value)
    }
}
