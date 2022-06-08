package com.example.mycleanarchitecture.presentation.screens.list.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.recyclerview.widget.RecyclerView
import coil.compose.AsyncImage
import com.example.mycleanarchitecture.presentation.screens.list.model.SpacesView
import com.example.mycleanarchitecture.presentation.util.YOUTUBE_IMG_BASE_URL
import com.example.mycleanarchitecture.presentation.util.YOUTUBE_IMG_END_URL
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject
import kotlin.properties.Delegates

class SpaceListAdapter
@Inject() constructor(@ActivityContext private val context: Context) :
    RecyclerView.Adapter<SpaceListAdapter.ViewHolder>() {


    internal var spaceList: List<SpacesView> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    var onItemClick: ((Long) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val composeView = ComposeView(context)
        return ViewHolder(composeView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val space = spaceList[position]
        holder.bind(space)
    }

    override fun getItemCount() = spaceList.size

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val composeView = itemView as ComposeView
        fun bind(spacesView: SpacesView) {
            val path = "$YOUTUBE_IMG_BASE_URL${spacesView.youtubeVideoId}$YOUTUBE_IMG_END_URL"
            composeView.setContent {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .background(Color(0xFF171A21))
                        .clickable {
                            onItemClick?.invoke(spaceList[adapterPosition].flightNumber)
                        }
                ) {
                    AsyncImage(
                        modifier = Modifier.fillMaxWidth().height(200.dp),
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
            /* binding.launchNameTextView.text = spacesView.missionName
             binding.spaceDateTextView.text = spacesView.launchDate
             val path = "$YOUTUBE_IMG_BASE_URL${spacesView.youtubeVideoId}$YOUTUBE_IMG_END_URL"
             binding.spaceImageView.loadRoundImage(
                 context, path,
                 LAUNCH_IMAGE_CORNER_RADIUS
             )
             itemView.setOnClickListener {
                 onItemClick?.invoke(spaceList[adapterPosition].flightNumber)
             }*/
        }
    }

    companion object {
        const val LAUNCH_IMAGE_CORNER_RADIUS = 24
    }
}
