package ru.example.mapsretrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.example.mapsretrofit.network.NetService
import ru.example.mapsretrofit.network.TagRepository
import ru.example.mapsretrofit.network.TagViewModel
import ru.mrapple100.mapsretrofit.network.ResponseHandler
import java.util.concurrent.TimeUnit

object RetrofitSingletone {
    val baseUrl = "https://maps.rtuitlab.dev"
    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .build()
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service = retrofit.create(NetService::class.java)
    val handler = ResponseHandler()
    val tagRepository = TagRepository(service, handler)
    val tagsviewModel = TagViewModel(tagRepository)
}