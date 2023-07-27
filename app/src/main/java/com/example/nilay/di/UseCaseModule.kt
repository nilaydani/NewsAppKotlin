package com.example.nilay.di

import com.example.nilay.domain.repository.NewsRepository
import com.example.nilay.domain.usecase.GetNewsListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    // This method provides an instance of GetNewsListUseCase, a use case class in the application architecture.
    // It takes a NewsRepository dependency as a parameter, which will be provided by another module.
    @Provides
    fun provideGetNewsListUseCase(newsRepository: NewsRepository): GetNewsListUseCase {
        return GetNewsListUseCase(newsRepository)
    }
}