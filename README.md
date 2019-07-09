The app loads relatively big JSON file with cities in memory then indexes data into *Trie* and allows to filter this data by prefix with close to no delay.

It follows *MVP* architecture. Uses *Koin* for DI/service locator and *Kotlin Coroutines* for asynchronous jobs.
