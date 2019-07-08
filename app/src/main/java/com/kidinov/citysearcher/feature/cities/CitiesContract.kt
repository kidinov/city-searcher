package com.kidinov.citysearcher.feature.cities

import com.kidinov.citysearcher.common.base.BasePresenter
import com.kidinov.citysearcher.common.model.City
import com.kidinov.citysearcher.common.model.Coordinates

interface CitiesContract {
    interface View {
        fun updateList()
        fun showEmptyState()
        fun showMapWithCoordinates(coordinates: Coordinates)
    }

    interface Presenter : BasePresenter {
        fun onPrefixChanged(prefix: String)
        fun getItemCount(): Int
        fun bindView(holder: ItemView, position: Int)
        fun getItemId(position: Int): Long
    }

    interface Repo {
        suspend fun performFiltering(prefix: String): List<City>
    }

    interface ItemView {
        fun bindView(city: String, country: String, coordinates: String, onClick: () -> Unit)
    }
}