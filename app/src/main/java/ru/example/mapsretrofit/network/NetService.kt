package ru.example.mapsretrofit.network

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Part
import ru.example.mapsretrofit.network.Model.Tag

interface NetService {
    @GET("/api/tags")
    suspend fun getTags():List<Tag>
    @POST("/api/tags")
    suspend fun createTag(
        @Part("latitude") latitude:Float,
        @Part("longitude") longitude:Float,
        @Part("description") description:String,
        )
}