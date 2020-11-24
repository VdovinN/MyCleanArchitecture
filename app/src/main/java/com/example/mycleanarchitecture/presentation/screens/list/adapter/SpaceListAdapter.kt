package com.example.mycleanarchitecture.presentation.screens.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mycleanarchitecture.R
import com.example.mycleanarchitecture.presentation.util.extension.loadRoundImage
import com.example.mycleanarchitecture.presentation.util.YOUTUBE_IMG_BASE_URL
import com.example.mycleanarchitecture.presentation.util.YOUTUBE_IMG_END_URL
import com.example.mycleanarchitecture.presentation.screens.list.model.SpacesView
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.android.synthetic.main.launch_item_layout.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

class SpaceListAdapter
@Inject() constructor(@ActivityContext private val context: Context) : RecyclerView.Adapter<SpaceListAdapter.ViewHolder>() {


    internal var spaceList: List<SpacesView> by Delegates.observable(emptyList()) {
            _, _, _ -> notifyDataSetChanged()
    }

    var onItemClick: ((Long) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.launch_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val space = spaceList[position]
        holder.bind(space)
    }

    override fun getItemCount() = spaceList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(spacesView: SpacesView) {
            itemView.launch_name_text_view.text = spacesView.missionName
            itemView.spaceDateTextView.text = spacesView.launchDate
            val path = "$YOUTUBE_IMG_BASE_URL${spacesView.youtubeVideoId}$YOUTUBE_IMG_END_URL"
            itemView.spaceImageView.loadRoundImage(context, path,
                LAUNCH_IMAGE_CORNER_RADIUS
            )
            itemView.setOnClickListener {
                onItemClick?.invoke(spaceList[adapterPosition].flightNumber)
            }
        }
    }

    companion object {
        const val LAUNCH_IMAGE_CORNER_RADIUS = 24
    }
}
