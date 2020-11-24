package com.example.mycleanarchitecture.presentation.screens.detail

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.mycleanarchitecture.R
import com.example.mycleanarchitecture.presentation.screens.detail.model.SpaceDetailsView
import com.example.mycleanarchitecture.presentation.util.*
import com.example.mycleanarchitecture.presentation.util.extension.loadImage
import com.example.mycleanarchitecture.presentation.util.extension.setTextOrHide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.detail_toolbar.*
import kotlinx.android.synthetic.main.fragment_space_details.*

@AndroidEntryPoint
class SpaceDetailsFragment : Fragment(R.layout.fragment_space_details) {

    private val viewModel: SpaceDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val flightNumber = arguments?.get(SPACE_KEY) as Long
        initView()

        backButton.setOnClickListener { viewModel.backPressed() }


        viewModel.data.observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.navigateBack.observe(
            viewLifecycleOwner,
            Observer { findNavController().popBackStack() }
        )
        viewModel.openVideo.observe(viewLifecycleOwner, Observer { displayVideo(it) })
        viewModel.openWiki.observe(viewLifecycleOwner, Observer { displayWikiPage(it) })

        viewModel.getSpaceByFlightNumber(flightNumber)
    }

    private fun renderData(spaceDetailsView: SpaceDetailsView) {
        title.setTextOrHide(spaceDetailsView.missionName)
        spaceDetailsTextView.setTextOrHide(spaceDetailsView.details)
        spaceDateTextView.setTextOrHide(spaceDetailsView.launchDate)
        spacePayloadMassTextView.setTextOrHide(
            R.string.payload_mass,
            spaceDetailsView.payloadMass
        )
        spaceRocketNameTextView.setTextOrHide(spaceDetailsView.rocketName)
        spaceWikiTextView.setTextOrHide(spaceDetailsView.wikiLink)
        val path = "$YOUTUBE_IMG_BASE_URL${spaceDetailsView.youtubeVideoId}$YOUTUBE_IMG_END_URL"
        spaceImageView.loadImage(path)
        spaceImageView.setOnClickListener { viewModel.openVideo(spaceDetailsView.youtubeVideoId) }

        spaceWikiTextView.setOnClickListener { viewModel.openWiki(spaceDetailsView.wikiLink) }
    }

    private fun displayWikiPage(wikiLink: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(wikiLink))
        context?.startActivity(browserIntent)
    }

    private fun displayVideo(videoId: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("$YOUTUBE_VND_URL$videoId"))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            context?.startActivity(intent)
        } catch (ex: ActivityNotFoundException) {
            context?.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("$YOUTUBE_BASE_URL$videoId")
                )
            )
        }
    }

    private fun initView() {
        spaceDetailsTextView.movementMethod = ScrollingMovementMethod.getInstance()
    }
}