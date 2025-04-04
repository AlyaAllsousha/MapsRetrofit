package ru.example.mapsretrofit.network

import ru.mrapple100.mapsretrofit.network.ResponseHandler

class TagRepository(
    val service:NetService,
    val handle: ResponseHandler
) {
    suspend fun fetchTags() = handle{
        service.getTags()
    }
    suspend fun createTag(latitude:Float,longitude:Float, description:String) = handle{
        service.createTag(latitude,longitude, description)
    }
}