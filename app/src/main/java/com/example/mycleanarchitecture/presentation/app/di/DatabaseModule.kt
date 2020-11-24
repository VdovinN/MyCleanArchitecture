package com.example.mycleanarchitecture.presentation.app.di

import android.content.Context
import androidx.room.Room
import com.example.mycleanarchitecture.data.source.DatabaseDataSource
import com.example.mycleanarchitecture.framework.database.DatabaseDataSourceImpl
import com.example.mycleanarchitecture.framework.database.SpaceDatabase
import com.example.mycleanarchitecture.framework.database.dao.SpaceDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    private const val DB_NAME = "spaces_db.db"

    @Provides
    fun provideSpaceDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, SpaceDatabase::class.java, DB_NAME).build()

    @Provides
    fun provideSpaceDao(database: SpaceDatabase) = database.spaceDao()

    @Provides
    fun provideDatabaseDataSource(spaceDao: SpaceDao): DatabaseDataSource =
        DatabaseDataSourceImpl(spaceDao)
}