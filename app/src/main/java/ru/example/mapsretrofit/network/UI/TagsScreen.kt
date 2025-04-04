package ru.example.mapsretrofit.network.UI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.example.mapsretrofit.RetrofitSingletone
import ru.example.mapsretrofit.network.TagViewModel

@Composable
fun TagsScreen(
     tagsViewModel: TagViewModel = RetrofitSingletone.tagsviewModel
){
    val tags = tagsViewModel._tagsStateFlow.collectAsState().value
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Button(onClick = {
            tagsViewModel.fetchTags()
        }) {
            Text(text = "Get Tags")
        }
        if(tags != null) {
            LazyColumn {
                items(tags) {tag->
                    Card (
                        modifier = Modifier
                            .defaultMinSize(200.dp, 200.dp)
                    ){
                        Column (
                            modifier = Modifier
                                .padding(5.dp)
                        ){
                            Text(text = tag.description)
                            Text(text = ""+tag.longitude)
                            Text(text = ""+tag.latitude)
                            Text(text= tag.image?:"wi-wi-wi")
                        }
                    }
                }
            }
        }
        else{
            Text(text = "Problems???")

        }
    }
}