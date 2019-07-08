package com.kidinov.citysearcher.feature.cities.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kidinov.citysearcher.R
import com.kidinov.citysearcher.feature.cities.CitiesContract

class CitiesAdapter(
    private val presenter: CitiesContract.Presenter
) : RecyclerView.Adapter<CitiesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = presenter.getItemCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder, position)
    }

    override fun getItemId(position: Int): Long = presenter.getItemId(position)

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view), CitiesContract.ItemView {
        override fun bindView(city: String, country: String, coordinates: String, onClick: () -> Unit) {
            view.setOnClickListener { onClick() }
            view.findViewById<TextView>(R.id.tvCityName).text = city
            view.findViewById<TextView>(R.id.tvCountryName).text = country
            view.findViewById<TextView>(R.id.tvCoordinates).text = coordinates
        }
    }
}