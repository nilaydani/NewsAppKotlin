package com.example.nilay.domain.repository

import com.example.nilay.data.model.news.NewsResponse
import retrofit2.Response

/**
 * Interface that defines the methods to be implemented by a news repository.
 */
interface NewsRepository {

    /**
     * Retrieves the list of news articles for the specified country, using the provided token for authentication.
     *
     * @param token The token to use for authentication.
     * @param country The country for which to retrieve the news articles.
     *
     * @return The response containing the list of news articles, or an error if the request failed.
     */
    suspend fun getNews(token: String, country: String,page: Int): Response<NewsResponse>
    suspend fun getNewsSearch(token: String, country: String,page: Int, query:String): Response<NewsResponse>

}