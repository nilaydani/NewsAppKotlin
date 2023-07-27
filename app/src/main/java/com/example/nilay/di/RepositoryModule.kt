package com.example.nilay.di

import com.example.nilay.data.repository.NewsRepositoryImpl
import com.example.nilay.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    // Provides an instance of NewsRepository interface
    @Provides
    fun provideNewsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository {
        return newsRepositoryImpl
    }

}