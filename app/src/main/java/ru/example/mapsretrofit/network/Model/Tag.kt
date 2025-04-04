package ru.example.mapsretrofit.network.Model

import com.google.gson.annotations.Expose

data class Tag (
    @Expose
    val id:String,
    @Expose
    val latitude:Float,
    @Expose
    val longitude:Float,
    @Expose
    val description:String,
    @Expose
    val image:String,
    @Expose
    val likes:Int,
    @Expose
    val is_liked:Boolean
)
