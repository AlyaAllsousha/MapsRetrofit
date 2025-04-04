package ru.example.mapsretrofit.network

import ru.mrapple100.mapsretrofit.network.ResponseHandler

class TagRepository(
    val service:NetService,
    val repository: ResponseHandler
) {
    suspend fun fetchTags(){
        service.getTags()
    }
}