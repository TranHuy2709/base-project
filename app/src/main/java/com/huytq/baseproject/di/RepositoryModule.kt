package com.huytq.baseproject.di

import com.huytq.baseproject.data.repositoryimpl.SampleRepositoryImpl
import com.huytq.baseproject.domain.repository.SampleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideSampleRepository(sampleRepositoryImpl: SampleRepositoryImpl): SampleRepository

}