package ru.example.mapsretrofit.network.UI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.example.mapsretrofit.RetrofitSingletone
import ru.example.mapsretrofit.network.TagViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagsScreen(
     tagsViewModel: TagViewModel = RetrofitSingletone.tagsviewModel
){
    val tags = tagsViewModel._tagsStateFlow.collectAsState().value
    var latitude by remember { mutableStateOf("") }
    var longtitude by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top

    ) {
        Text(text = "latitude")
        TextField(
            value = latitude.toString(),
            onValueChange = {it -> latitude = it} )
        Text(text = "longtitude")

        TextField(
            value = longtitude ,
            onValueChange = {it -> longtitude=it}
        )
        Text(text = "description")
        TextField(
            value = description ,
            onValueChange = {it -> description=it}
        )
        Button(onClick = {
            tagsViewModel.createTag(latitude.toFloat(), longtitude.toFloat(), description)
        }) {
            Text(text = "Add")
        }
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
                            AsyncImage(
                                model = RetrofitSingletone.baseUrl+tag.image,
                                contentDescription = "my image"
                            )
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