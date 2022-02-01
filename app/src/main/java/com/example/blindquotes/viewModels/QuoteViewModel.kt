package com.example.blindquotes.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blindquotes.rest.APIClient
import com.example.blindquotes.rest.models.Quote
import kotlinx.coroutines.launch

class QuoteViewModel : ViewModel() {

    val client: APIClient = APIClient()

    private val quotes: MutableLiveData<Quote> by lazy {
        MutableLiveData<Quote>().also {
            loadQuotes()
        }
    }

    fun getQuotes(): LiveData<Quote> {
        return quotes
    }

    private fun loadQuotes() = viewModelScope.launch {
        val qs: Quote = client.getQuote()
        quotes.value = qs
    }
}