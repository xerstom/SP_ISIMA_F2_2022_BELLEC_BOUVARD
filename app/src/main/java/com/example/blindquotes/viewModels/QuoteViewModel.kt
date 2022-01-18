package com.example.blindquotes.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blindquotes.rest.APIClient
import com.example.blindquotes.rest.Quote
import kotlinx.coroutines.launch

class QuoteViewModel : ViewModel() {

    val client: APIClient = APIClient()

    private val quotes: MutableLiveData<List<Quote>> by lazy {
        MutableLiveData<List<Quote>>().also {
            loadQuotes()
        }
    }

    fun getQuotes(): LiveData<List<Quote>> {
        return quotes
    }

    private fun loadQuotes() = viewModelScope.launch {
        val qs: List<Quote> = client.getQuotes()
        quotes.value = qs
    }
}