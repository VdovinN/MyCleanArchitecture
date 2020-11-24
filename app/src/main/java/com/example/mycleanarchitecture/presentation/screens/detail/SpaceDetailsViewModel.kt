package com.example.mycleanarchitecture.presentation.screens.detail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.mycleanarchitecture.domain.Space
import com.example.mycleanarchitecture.usecase.GetSpaceDetailsUseCase
import com.example.mycleanarchitecture.presentation.util.click.SingleLiveEvent
import com.example.mycleanarchitecture.presentation.util.mapper.toSpaceDetailsView
import com.example.mycleanarchitecture.presentation.base.BaseViewModel
import com.example.mycleanarchitecture.presentation.screens.detail.model.SpaceDetailsView

class SpaceDetailsViewModel
@ViewModelInject constructor(
    private val getSpaceDetailsUseCase: GetSpaceDetailsUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    val navigateBack = SingleLiveEvent<Unit>()
    val openVideo = SingleLiveEvent<String>()
    val openWiki = SingleLiveEvent<String>()

    private val _data: MutableLiveData<SpaceDetailsView> = MutableLiveData()

    val data: LiveData<SpaceDetailsView>
        get() = _data

    fun getSpaceByFlightNumber(flightNumber: Long) =
        getSpaceDetailsUseCase(GetSpaceDetailsUseCase.Params(flightNumber)) {
            it.fold(::handleFailure, ::handleSpaceDetails)
        }

    fun openVideo(videoId: String?) {
        openVideo.value = videoId
    }

    fun openWiki(wikiUrl: String?) {
        openWiki.value = wikiUrl
    }

    fun backPressed() {
        navigateBack.executeEmpty()
    }

    private fun handleSpaceDetails(space: Space) {
        _data.value = space.toSpaceDetailsView()
    }

    override fun onCleared() {
        super.onCleared()
        getSpaceDetailsUseCase.cancel()
    }
}
