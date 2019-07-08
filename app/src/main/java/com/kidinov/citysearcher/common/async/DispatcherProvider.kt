package com.kidinov.citysearcher.common.async

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DispatcherProviderImpl : DispatcherProvider {
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main

    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
}

interface DispatcherProvider {
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
}
