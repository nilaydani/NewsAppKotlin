package com.example.nilay.presentation.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nilay.common.Resource
import com.example.nilay.common.constants.API_NEWS_TOKEN
import com.example.nilay.domain.model.Article
import com.example.nilay.domain.usecase.GetNewsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsListUseCase: GetNewsListUseCase
) : ViewModel() {
    // Private MutableStateFlow for articles list
    private val _news = MutableStateFlow<Resource<List<Article>>>(Resource.Loading)
    // Public StateFlow for articles list that can only be observed externally
    val news: StateFlow<Resource<List<Article>>> = _news

    val page = MutableStateFlow(1)

    // Private MutableStateFlow for selected country
    private val _country = MutableStateFlow("us")
    // Public StateFlow for selected country that can only be observed externally
    val country: StateFlow<String> = _country

    // Function to update selected country
    fun setCountry(country: String) {
        _country.value = country
        getNews()
    }

    init {
        getNews()
    }

    // Function to get news for selected country
    fun getNews() {
        // Use viewModelScope to launch a coroutine that will automatically be cancelled when the ViewModel is cleared
        viewModelScope.launch {
            // Collect the news from the use case using the selected country
            getNewsListUseCase(API_NEWS_TOKEN, _country.value,page.value)
                .collect { resource ->
                    // Update the private MutableStateFlow with the result from the use case
                    _news.value = resource
                }
        }
    }

}