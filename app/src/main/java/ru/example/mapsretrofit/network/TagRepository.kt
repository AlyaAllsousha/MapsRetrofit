package ru.example.mapsretrofit.network

import ru.mrapple100.mapsretrofit.network.ResponseHandler

class TagRepository(
    val service:NetService,
    val handle: ResponseHandler
) {
    suspend fun fetchTags() = handle{
        service.getTags()
    }
}