package com.example.mycleanarchitecture.presentation.screens.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.mycleanarchitecture.domain.Space
import com.example.mycleanarchitecture.presentation.base.BaseViewModel
import com.example.mycleanarchitecture.presentation.screens.detail.model.SpaceDetailsView
import com.example.mycleanarchitecture.presentation.util.mapper.toSpaceDetailsView
import com.example.mycleanarchitecture.usecase.GetSpaceDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SpaceDetailsViewModel
@Inject constructor(
    private val getSpaceDetailsUseCase: GetSpaceDetailsUseCase,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    private val _data: MutableLiveData<SpaceDetailsView> = MutableLiveData()

    val data: LiveData<SpaceDetailsView>
        get() = _data

    fun getSpaceByFlightNumber(flightNumber: Long) =
        getSpaceDetailsUseCase(GetSpaceDetailsUseCase.Params(flightNumber)) {
            it.fold(::handleFailure, ::handleSpaceDetails)
        }

    private fun handleSpaceDetails(space: Space) {
        _data.value = space.toSpaceDetailsView()
    }

    override fun onCleared() {
        super.onCleared()
        getSpaceDetailsUseCase.cancel()
    }
}
