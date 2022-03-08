package com.example.blindquotes.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blindquotes.rest.APIClient
import com.example.blindquotes.rest.models.CompleteQuote
import com.example.blindquotes.rest.models.GuessQuote
import kotlinx.coroutines.launch

class CompleteQuoteViewModel : ViewModel() {

    private val client: APIClient = APIClient()
    private var category = "all"

    private val quote: MutableLiveData<CompleteQuote> by lazy {
        MutableLiveData<CompleteQuote>().also {
            loadQuote(it)
        }
    }

    fun getQuote(): LiveData<CompleteQuote> {
        return quote;
    }

    fun renew() {
        loadQuote(quote);
    }

    fun setCategory(category: String) {
        this.category = category
    }

    private fun loadQuote(liveData: MutableLiveData<CompleteQuote>) = viewModelScope.launch {
        liveData.value = client.getCompleteQuote(category)
    }
}