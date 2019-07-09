package com.kidinov.citysearcher.common.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.kidinov.citysearcher.common.misc.Config
import com.kidinov.citysearcher.common.model.City
import java.io.InputStreamReader
import java.util.LinkedList

interface JsonFileReader {
    fun readJson(): MutableList<City>
}

class JsonFileReaderImpl(
    private val gson: Gson,
    private val context: Context,
    private val config: Config
) : JsonFileReader {
    override fun readJson(): MutableList<City> {
        val jsonFileName = config.jsonFileName
        val reader = JsonReader(InputStreamReader(context.assets.open(jsonFileName)))
        val cities = LinkedList<City>()
        try {
            reader.beginArray()
            while (reader.hasNext()) {
                cities.add(gson.fromJson(reader, City::class.java))
            }
            reader.endArray()
            reader.close()
        } catch (e: Exception) {
            throw RuntimeException("Count read json file $jsonFileName")
        } finally {
            reader.close()
        }
        return cities
    }
}