package com.example.mycleanarchitecture.presentation.screens.list

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.mycleanarchitecture.presentation.util.exception.Failure
import com.example.mycleanarchitecture.R
import com.example.mycleanarchitecture.presentation.screens.list.adapter.SpaceListAdapter
import com.example.mycleanarchitecture.presentation.util.extension.gone
import com.example.mycleanarchitecture.presentation.util.extension.visible
import com.example.mycleanarchitecture.presentation.util.SPACE_KEY
import com.example.mycleanarchitecture.presentation.screens.list.model.SpacesView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_space_list.*
import javax.inject.Inject

@AndroidEntryPoint
class SpaceListFragment : Fragment(R.layout.fragment_space_list) {

    private val viewModel: SpaceListViewModel by viewModels()

    @Inject
    lateinit var adapter: SpaceListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingView.visible()
        initView()

        viewModel.data.observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.failure.observe(viewLifecycleOwner, Observer { renderFailure(it) })

        viewModel.navigateToDetail.observe(viewLifecycleOwner, Observer {
            navigateToDetails(it)
        })

        viewModel.getSpaces()
    }

    private fun renderFailure(failure: Failure) {
        when (failure) {
            Failure.NetworkConnectionError -> errorView.setError(
                R.drawable.ic_warning,
                getString(R.string.network_connection_error)
            )
            Failure.ListNotAvailableError -> errorView.setError(
                R.drawable.ic_warning,
                getString(R.string.list_not_available)
            )
            Failure.ServerError -> errorView.setError(
                R.drawable.ic_warning,
                getString(R.string.server_error)
            )
        }
        loadingView.gone()
    }

    private fun renderData(spacesView: List<SpacesView>) {
        adapter.spaceList = spacesView
        loadingView.gone()
    }

    private fun navigateToDetails(flightNumber: Long?) {
        findNavController().navigate(
            R.id.action_spaceListFragment_to_spaceDetails,
            bundleOf(SPACE_KEY to flightNumber)
        )
    }

    private fun initView(){
        launches_recycler_view.adapter = adapter
        launches_recycler_view.addItemDecoration(
            DividerItemDecoration(
                launches_recycler_view.context,
                DividerItemDecoration.VERTICAL
            )
        )

        adapter.onItemClick = { viewModel.openSpaceDetails(it) }
    }
}