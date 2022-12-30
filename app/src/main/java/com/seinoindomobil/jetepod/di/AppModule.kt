package com.seinoindomobil.jetepod.di

import android.content.Context
import com.seinoindomobil.jetepod.BuildConfig.BASE_URL
import com.seinoindomobil.jetepod.data.local.datastore.DataStoreRepo
import com.seinoindomobil.jetepod.data.local.datastore.DataStoreRepoImpl
import com.seinoindomobil.jetepod.data.remote.ApiClient
import com.seinoindomobil.jetepod.data.remote.repository.LoginRepositoryImpl
import com.seinoindomobil.jetepod.domain.repo.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideEpodApi(): ApiClient {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiClient::class.java)
    }

    @Provides
    @Singleton
    fun providesDatstoreRepo(
        @ApplicationContext context: Context
    ): DataStoreRepo = DataStoreRepoImpl(context)


    @Provides
    @Singleton
    fun provideCoinRepository(api: ApiClient): LoginRepository {
        return LoginRepositoryImpl(api)
    }
}