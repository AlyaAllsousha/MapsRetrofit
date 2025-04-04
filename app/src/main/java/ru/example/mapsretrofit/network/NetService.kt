package ru.example.mapsretrofit.network

import retrofit2.http.GET
import ru.example.mapsretrofit.network.Model.Tag

interface NetService {
    @GET("/api/tags")
    suspend fun getTags():List<Tag>

}