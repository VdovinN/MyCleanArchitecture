package com.example.mycleanarchitecture.presentation.screens.list

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.mycleanarchitecture.domain.Space
import com.example.mycleanarchitecture.usecase.GetSpacesUseCase
import com.example.mycleanarchitecture.usecase.base.UseCase
import com.example.mycleanarchitecture.presentation.util.click.SingleLiveEvent
import com.example.mycleanarchitecture.presentation.util.mapper.toSpacesView
import com.example.mycleanarchitecture.presentation.base.BaseViewModel
import com.example.mycleanarchitecture.presentation.screens.list.model.SpacesView

class SpaceListViewModel
@ViewModelInject constructor(
    private val getSpacesUseCase: GetSpacesUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    val navigateToDetail = SingleLiveEvent<Long>()

    private val _data: MutableLiveData<List<SpacesView>> = MutableLiveData()
    val data: LiveData<List<SpacesView>>
        get() = _data

    fun getSpaces() {
        if (data.value.isNullOrEmpty()) {
            getSpacesUseCase(UseCase.None()) { it.fold(::handleFailure, ::handleSpaces) }
        }
    }
    private fun handleSpaces(spaceList: List<Space>) {
        _data.value = spaceList.map {
            it.toSpacesView()
        }
    }

    fun openSpaceDetails(flightNumber: Long) {
        navigateToDetail.value = flightNumber
    }

    override fun onCleared() {
        super.onCleared()
        getSpacesUseCase.cancel()
    }
}