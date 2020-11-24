package com.example.mycleanarchitecture.presentation.activity.di

import com.example.mycleanarchitecture.data.repository.SpaceRepository
import com.example.mycleanarchitecture.usecase.GetSpaceDetailsUseCase
import com.example.mycleanarchitecture.usecase.GetSpacesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetSpaces(spaceRepository: SpaceRepository) = GetSpacesUseCase(spaceRepository)

    @Provides
    fun provideGetSpaceDetails(spaceRepository: SpaceRepository) =
        GetSpaceDetailsUseCase(spaceRepository)
}