package com.example.mycleanarchitecture.presentation.screens.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.mycleanarchitecture.R
import com.example.mycleanarchitecture.presentation.util.exception.Failure

@Composable
fun ErrorScreen(failure: Failure) {
    val imageId: Int
    val messageId: Int
    when (failure) {
        Failure.NetworkConnectionError -> {
            imageId = R.drawable.ic_warning
            messageId = R.string.network_connection_error
        }
        Failure.ListNotAvailableError -> {
            imageId = R.drawable.ic_warning
            messageId = R.string.list_not_available
        }
        Failure.ServerError -> {
            imageId = R.drawable.ic_warning
            messageId = R.string.server_error
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = imageId), contentDescription = null)
        Text(
            text = stringResource(id = messageId), color = Color(0xFF9DD8F6),
            fontSize = 14.sp
        )
    }
}