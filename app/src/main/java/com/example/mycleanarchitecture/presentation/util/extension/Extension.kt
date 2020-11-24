package com.example.mycleanarchitecture.presentation.util.extension

import android.content.Context
import android.os.Build
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.mycleanarchitecture.R
import com.example.mycleanarchitecture.presentation.util.image.GlideApp
import com.example.mycleanarchitecture.presentation.util.DATE_FORMAT
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun TextView.setTextOrHide(text: String?) {
    return if (text.isNullOrEmpty() || text.isNullOrBlank()) {
        this.visibility = View.GONE
    } else {
        this.text = text
    }
}

fun TextView.setTextOrHide(resId: Int, vararg text: Any?) {
    if (text.isNullOrEmpty()) {
        this.visibility = View.GONE
    } else {
        this.text = context.getString(resId, *text)
    }
}

fun View.showSnackBar(@StringRes messageRes: Int, length: Int = Snackbar.LENGTH_LONG) {
    val snackBar = Snackbar.make(this, resources.getString(messageRes), length)
    snackBar.show()
}

fun AppCompatActivity.setupStatusBarColor() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.generalGray)
    }
}

fun ImageView.loadRoundImage(context: Context, url: String, cornerRadius: Int) {
    GlideApp.with(context)
        .load(url)
        .placeholder(R.drawable.ic_rocket)
        .error(R.drawable.ic_rocket)
        .transform(CenterCrop(), RoundedCorners(cornerRadius))
        .into(this)
}

fun ImageView.loadImage(url: String) {
    GlideApp.with(context)
        .load(url)
        .placeholder(R.drawable.ic_rocket)
        .error(R.drawable.ic_rocket)
        .into(this)
}

fun Long.convertTimestampToFormattedDate(): String {
    val timestamp = this
    val date = Date(timestamp * 1000)
    val formatter = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
    return formatter.format(date)
}