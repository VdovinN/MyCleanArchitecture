package com.example.mycleanarchitecture.presentation.screens.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.mycleanarchitecture.R
import com.example.mycleanarchitecture.presentation.screens.error.ErrorScreen
import com.example.mycleanarchitecture.presentation.screens.list.model.SpacesView
import com.example.mycleanarchitecture.presentation.util.YOUTUBE_IMG_BASE_URL
import com.example.mycleanarchitecture.presentation.util.YOUTUBE_IMG_END_URL

@Composable
fun SpaceListScreen(
    viewModel: SpaceListViewModel = hiltViewModel(),
    onItemClick: ((Long) -> Unit)?
) {
    val viewState = viewModel.data.observeAsState()
    val failState = viewModel.failure.observeAsState()
    LaunchedEffect(key1 = Unit, block = {
        viewModel.getSpaces()
    })
    Box(modifier = Modifier.fillMaxSize().background(color = Color(0xFF171A21)), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = Color(0xFF2F5382))
    }
    viewState.value?.let {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(color = Color(0xFF1D1E22)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.space_x_logo),
                    contentDescription = null
                )
            }
            LaunchesListView(
                launches = it,
                onItemClick = { flightNumber ->
                    onItemClick?.invoke(flightNumber)
                })
        }
    }
    failState.value?.let {
        ErrorScreen(failure = it)
    }
}

@Composable
fun LaunchesListView(launches: List<SpacesView>, onItemClick: ((Long) -> Unit)?) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        launches.forEach { spacesView ->
            item {
                val path =
                    "$YOUTUBE_IMG_BASE_URL${spacesView.youtubeVideoId}$YOUTUBE_IMG_END_URL"
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF171A21))
                        .padding(horizontal = 8.dp)
                        .clickable {
                            onItemClick?.invoke(spacesView.flightNumber)
                        }
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentScale = ContentScale.Crop,
                        model = path,
                        contentDescription = null
                    )
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Text(
                            text = spacesView.missionName,
                            color = Color(0xFF9DD8F6),
                            fontSize = 14.sp
                        )
                        Text(
                            text = spacesView.launchDate,
                            color = Color(0xFF969696),
                            fontSize = 12.sp,
                            modifier = Modifier
                                .background(Color(0XFF424242))
                                .padding(horizontal = 4.dp)
                        )
                    }
                }
            }
        }
    }
}
