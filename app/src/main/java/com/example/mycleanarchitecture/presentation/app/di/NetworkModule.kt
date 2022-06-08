package com.example.mycleanarchitecture.presentation.app.di

import android.content.Context
import com.example.mycleanarchitecture.data.connection.InternetConnection
import com.example.mycleanarchitecture.data.source.NetworkDataSource
import com.example.mycleanarchitecture.framework.connection.InternetConnectionImpl
import com.example.mycleanarchitecture.framework.network.NetworkDataSourceImpl
import com.example.mycleanarchitecture.framework.network.api.SpaceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.spacexdata.com/"

    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
    }

    @Provides
    fun provideSpaceApi(okHttpClient: OkHttpClient): SpaceApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(SpaceApi::class.java)
    }

    @Provides
    fun provideNetworkDataSource(spaceApi: SpaceApi): NetworkDataSource =
        NetworkDataSourceImpl(spaceApi)

    @Provides
    fun provideNetworkConnection(@ApplicationContext context: Context): InternetConnection =
        InternetConnectionImpl(context)
}