package ru.example.mapsretrofit.network

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.example.mapsretrofit.network.Model.Tag

class TagViewModel(val tagRepository:TagRepository):ViewModel(){
    val tagsMutableStateFlow = MutableStateFlow<List<Tag>?>(null)
    val _tagsStateFlow = tagsMutableStateFlow.asStateFlow()
    fun fetchTags()  = GlobalScope.launch {
        tagRepository.fetchTags().handle(
            onSuccess = {tags->
                tagsMutableStateFlow.value =tags
            },
            onError = {
                Log.d("TAGSVIEWMODEL", it.toString())
            }
        )
    }
}