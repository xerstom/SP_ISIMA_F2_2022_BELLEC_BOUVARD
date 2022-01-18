package com.example.blindquotes.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blindquotes.rest.APIClient
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {

    val client: APIClient = APIClient()

    private val categories: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>().also {
            loadCategories()
        }
    }

    fun getCategories(): LiveData<List<String>> {
        return categories
    }

    private fun loadCategories() = viewModelScope.launch {
        val categs: List<String> = client.getCategories()
        categories.value = categs
    }
}