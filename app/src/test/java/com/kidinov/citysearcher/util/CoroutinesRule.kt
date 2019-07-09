package com.kidinov.citysearcher.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@UseExperimental(ObsoleteCoroutinesApi::class)
class CoroutinesRule : TestWatcher() {
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    override fun starting(description: Description?) {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    override fun finished(description: Description?) {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }
}