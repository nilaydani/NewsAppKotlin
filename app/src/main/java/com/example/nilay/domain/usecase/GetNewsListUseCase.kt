package com.example.nilay.domain.usecase

import com.example.nilay.common.Resource
import com.example.nilay.data.model.news.toArticle
import com.example.nilay.domain.model.Article
import com.example.nilay.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetNewsListUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(token: String, country: String,page: Int): Flow<Resource<List<Article>>> {
        return flow {
            emit(Resource.Loading) // emit loading state

            // call the getTopHeadlines API method
            val newsResponse = newsRepository.getNews(token,country,page)

            if (newsResponse.isSuccessful) { // check if the response is successful
                val articles = newsResponse.body()?.articles?.map { it.toArticle().apply {

                    // remplace les valeurs vides par "N/A"
                            content = content.takeIf { it?.isNotEmpty() == true } ?: "No Description Available"

                            author = author.takeIf { it?.isNotEmpty() == true } ?: "No Author Available"

                            title = title.takeIf { it?.isNotEmpty() == true } ?: "No Title Available"
                    }
                }
                if (articles != null) {
                    emit(Resource.Success(articles)) // emit success state with filtered articles
                } else {
                    emit(Resource.Error("Error filtering articles"))
                }
            } else {
                emit(Resource.Error("Error fetching news")) // emit error state
            }
        }.catch { e ->
            emit(Resource.Error(e.message.toString())) // catch and emit any exception as error state
        }.flowOn(Dispatchers.IO) // run the flow on the IO dispatcher
    }
}