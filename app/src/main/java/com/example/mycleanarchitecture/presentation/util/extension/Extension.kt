package com.example.mycleanarchitecture.presentation.util.extension

import com.example.mycleanarchitecture.presentation.util.DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.*


fun Long.convertTimestampToFormattedDate(): String {
    val timestamp = this
    val date = Date(timestamp * 1000)
    val formatter = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
    return formatter.format(date)
}