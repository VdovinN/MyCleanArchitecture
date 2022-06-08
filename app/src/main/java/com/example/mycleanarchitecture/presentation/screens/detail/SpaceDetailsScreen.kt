package com.example.mycleanarchitecture.presentation.screens.detail

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.mycleanarchitecture.R
import com.example.mycleanarchitecture.presentation.util.YOUTUBE_BASE_URL
import com.example.mycleanarchitecture.presentation.util.YOUTUBE_IMG_BASE_URL
import com.example.mycleanarchitecture.presentation.util.YOUTUBE_IMG_END_URL
import com.example.mycleanarchitecture.presentation.util.YOUTUBE_VND_URL

@Composable
fun SpaceDetailsScreen(
    viewModel: SpaceDetailsViewModel = hiltViewModel(),
    launchId: Long,
    navController: NavController
) {
    val viewState = viewModel.data.observeAsState()
    LaunchedEffect(key1 = Unit, block = {
        viewModel.getSpaceByFlightNumber(launchId)
    })
    viewState.value?.let { spaceDetailsView ->
        val context = LocalContext.current
        val path = "$YOUTUBE_IMG_BASE_URL${spaceDetailsView.youtubeVideoId}$YOUTUBE_IMG_END_URL"
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF171A21))) {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = null
                        )
                    }
                },
                backgroundColor = Color(0xFF1D1E22),
                contentColor = Color.White
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("$YOUTUBE_VND_URL${spaceDetailsView.youtubeVideoId}")
                        )
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        try {
                            context.startActivity(intent)
                        } catch (ex: ActivityNotFoundException) {
                            context.startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("$YOUTUBE_BASE_URL${spaceDetailsView.youtubeVideoId}")
                                )
                            )
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.4f),
                    contentScale = ContentScale.Crop,
                    model = path,
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_play),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth(0.25f)
                        .aspectRatio(1f)
                )
            }
            spaceDetailsView.launchDate.takeIf { it.isNotEmpty() }?.let {
                Text(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                        .padding(4.dp),
                    text = it.uppercase(),
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    color = Color(0xFF71707B)
                )
            }
            spaceDetailsView.details.takeIf { it.isNotEmpty() }?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    color = Color(0xFF9A9AA4)
                )
            }
            spaceDetailsView.rocketName.takeIf { it.isNotEmpty() }?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    color = Color(0xFF9DD8F6)
                )
            }
            spaceDetailsView.payloadMass.takeIf { it.isNotEmpty() }?.let {
                Text(
                    text = stringResource(R.string.payload_mass, it),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    color = Color(0xFF9DD8F6)
                )
            }
            spaceDetailsView.wikiLink.takeIf { it.isNotEmpty() }?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .clickable {
                            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                            context.startActivity(browserIntent)
                        },
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    color = Color(0xFF007AFF)
                )
            }
        }
    }
}
