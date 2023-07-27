package com.example.nilay.repository

import com.example.nilay.data.model.news.NewsResponse
import com.example.nilay.data.remote.NewsApi
import com.example.nilay.data.repository.NewsRepositoryImpl
import com.example.nilay.domain.repository.NewsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class NewsRepositoryImplTest {

    private lateinit var newsApi: NewsApi
    private lateinit var newsRepository: NewsRepository

    @Before
    fun setup() {
        newsApi = mockk()
        newsRepository = NewsRepositoryImpl(newsApi)
    }

    @Test
    fun `test getNews() should return response from newsApi`() = runBlocking {
        // Given
        val token = "your_token"
        val country = "your_country"
        val expectedResponse: Response<NewsResponse> = mockk()

        coEvery { newsApi.getTopHeadlines(token, country,1) } returns expectedResponse

        // When
        val result = newsRepository.getNews(token, country,1)

        // Then
        assertEquals(expectedResponse, result)
    }
}