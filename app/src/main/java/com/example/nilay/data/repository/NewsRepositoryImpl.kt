package com.example.nilay.data.repository

import com.example.nilay.data.model.news.NewsResponse
import com.example.nilay.data.remote.NewsApi
import com.example.nilay.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
): NewsRepository {

    /**
     * This function retrieves the top headlines news from the NewsAPI endpoint
     * for a given country and authorization token.
     * @param token Authorization token to access the NewsAPI endpoint
     * @param country Country code for which to retrieve the news
     * @return A Retrofit response object containing the news response from the endpoint
     */
    override suspend fun getNews(token: String, country: String, page: Int): Response<NewsResponse> {
        return withContext(Dispatchers.IO) {
            newsApi.getTopHeadlines(token, country,page)
        }
    }

    override suspend fun getNewsSearch(
        token: String,
        country: String,
        page: Int,
        query: String
    ): Response<NewsResponse> {
        return  withContext(Dispatchers.IO){
            newsApi.getSearchedNews(keyword = query,page,30,token)
        }
    }

}