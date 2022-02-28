package com.example.blindquotes.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blindquotes.rest.APIClient
import com.example.blindquotes.rest.models.Category
import com.example.blindquotes.rest.models.Quote
import kotlinx.coroutines.launch

class QuoteViewModel : ViewModel() {

    private val client: APIClient = APIClient()
    private var category = "all"

    private val quote: MutableLiveData<Quote> by lazy {
        MutableLiveData<Quote>().also {
            loadQuote(it)
        }
    }

    fun getQuote(): LiveData<Quote> {
        return quote
    }

    fun setCategory(category: String) {
        this.category = category
    }

    private fun loadQuote(liveData: MutableLiveData<Quote>) = viewModelScope.launch {
        liveData.value = client.getQuote(category)
    }
}