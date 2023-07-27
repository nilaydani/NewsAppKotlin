package com.example.nilay.data.remote

import com.example.nilay.common.constants.API_NEWS_TOKEN
import com.example.nilay.data.model.news.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    /**
     * Fetches the top headlines news articles from the News API for a specific country.
     *
     * @param apiKey the API key for accessing the News API
     * @param country the country for which to fetch top headlines news articles
     *
     * @return a [Response] object containing the top headlines news articles for the specified country
     */
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("apiKey") apiKey: String = API_NEWS_TOKEN,
        @Query("country") country: String,
        @Query("page") page: Int,
    ): Response<NewsResponse>
    @GET("everything")
    suspend fun getSearchedNews(
        @Query("q")
        keyword:String,
        @Query("page")
        page: Int,
        @Query("pageSize")
        pageSize:Int = 30,
        @Query("apiKey")
        apiKey: String = API_NEWS_TOKEN
    ):Response<NewsResponse>
}
