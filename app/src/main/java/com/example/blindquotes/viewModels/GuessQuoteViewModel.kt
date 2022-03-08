package com.example.blindquotes.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blindquotes.rest.APIClient
import com.example.blindquotes.rest.models.GuessQuote
import kotlinx.coroutines.launch

class GuessQuoteViewModel : ViewModel() {

    private val client: APIClient = APIClient()
    private var category = "all"

    private val quote: MutableLiveData<GuessQuote> by lazy {
        MutableLiveData<GuessQuote>().also {
            loadQuote(it)
        }
    }

    fun getQuote(): LiveData<GuessQuote> {
        return quote;
    }

    fun renew() {
        loadQuote(quote);
    }

    fun setCategory(category: String) {
        this.category = category
    }

    private fun loadQuote(liveData: MutableLiveData<GuessQuote>) = viewModelScope.launch {
        liveData.value = client.getGuessQuote(category)
    }
}