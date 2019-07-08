package com.kidinov.citysearcher.feature.cities.presenter

import com.kidinov.citysearcher.common.async.DispatcherProvider
import com.kidinov.citysearcher.common.model.City
import com.kidinov.citysearcher.feature.cities.CitiesContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.concurrent.ArrayBlockingQueue

class CitiesPresenter(
    private val view: CitiesContract.View,
    private val repo: CitiesContract.Repo,
    dispatcherProvider: DispatcherProvider
) : CitiesContract.Presenter {
    private val supervisorJob = SupervisorJob()
    private val ioScope = CoroutineScope(dispatcherProvider.io + supervisorJob)
    private val uiScope = CoroutineScope(dispatcherProvider.main + supervisorJob)

    private val messageQueue = ArrayBlockingQueue<String>(5)

    private val currentCities = mutableListOf<City>()

    override fun start() {
        ioScope.launch {
            while (this.isActive) {
                if (messageQueue.isNotEmpty()) {
                    val prefix = messageQueue.poll()
                    val cities = repo.performFiltering(prefix)
                    uiScope.launch {
                        if (cities.isEmpty()) view.showEmptyState()
                        with(currentCities) {
                            clear()
                            addAll(cities)
                        }
                        view.updateList()
                    }
                }
            }
        }
        messageQueue.put("")
    }

    override fun getItemCount(): Int = currentCities.size

    override fun getItemId(position: Int): Long = currentCities[position].id

    override fun bindView(holder: CitiesContract.ItemView, position: Int) {
        val city = currentCities[position]
        val coord = city.coord
        holder.bindView(city.name, city.country, "${coord.lat}/${coord.lon}") {
            view.showMapWithCoordinates(city.coord)
        }
    }

    override fun onPrefixChanged(prefix: String) {
        messageQueue.put(prefix.toLowerCase())
    }

    override fun stop() {
        supervisorJob.cancel()
    }
}