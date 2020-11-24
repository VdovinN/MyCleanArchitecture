package com.example.mycleanarchitecture.presentation.app.di

import com.example.mycleanarchitecture.data.connection.InternetConnection
import com.example.mycleanarchitecture.data.repository.SpaceRepository
import com.example.mycleanarchitecture.data.source.DatabaseDataSource
import com.example.mycleanarchitecture.data.source.NetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    fun provideSpaceRepository(
        networkDataSource: NetworkDataSource,
        databaseDataSource: DatabaseDataSource,
        internetConnection: InternetConnection
    ) = SpaceRepository(networkDataSource, databaseDataSource, internetConnection)

}