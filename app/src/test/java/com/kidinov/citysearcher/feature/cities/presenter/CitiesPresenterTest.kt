package com.kidinov.citysearcher.feature.cities.presenter

import com.kidinov.citysearcher.common.model.City
import com.kidinov.citysearcher.common.model.Coordinates
import com.kidinov.citysearcher.feature.cities.CitiesContract
import com.kidinov.citysearcher.util.CoroutinesRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.amshove.kluent.shouldEqualTo
import org.junit.Rule
import org.junit.Test

class CitiesPresenterTest {
    @Rule
    @JvmField
    val test = CoroutinesRule()

    private val view: CitiesContract.View = mock()
    private val repo: CitiesContract.Repo = mock {
        onBlocking { performFiltering("") }.thenReturn(testDataCities)
    }
    private val presenter = CitiesPresenter(view, repo)

    companion object {
        private val testDataCities = mutableListOf(
            City(1, "name2", "country1", Coordinates(0.1, 0.1)),
            City(2, "name1", "country2", Coordinates(0.2, 0.2))
        )
    }

    @Test
    fun `start Should implement search with empty prefix`() {
        // GIVEN
        presenter.onPrefixChanged("")

        // WHEN
        presenter.start()
        Thread.sleep(30)

        // THEN
        presenter.getItemCount() shouldEqualTo testDataCities.size
        verify(view).updateList()
    }

    @Test
    fun `getItemId Should return city id from stored list`() {
        // GIVEN
        presenter.onPrefixChanged("")
        presenter.start()
        Thread.sleep(30)

        // WHEN
        val res = presenter.getItemId(0)

        // THEN
        res shouldEqualTo testDataCities[0].id
    }

    @Test
    fun `bindView Should bind city information`() {
        // GIVEN
        presenter.onPrefixChanged("")
        presenter.start()
        Thread.sleep(30)
        val holder: CitiesContract.ItemView = mock()

        // WHEN
        presenter.bindView(holder, 1)

        // THEN
        val city = testDataCities[1]
        val coord = city.coord
        verify(holder).bindView(
            eq(city.name),
            eq(city.country),
            eq("${coord.lon}/${coord.lat}"),
            any()
        )
    }
}