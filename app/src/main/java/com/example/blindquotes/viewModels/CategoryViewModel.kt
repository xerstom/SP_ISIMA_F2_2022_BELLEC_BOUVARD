package com.example.blindquotes.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blindquotes.rest.APIClient
import com.example.blindquotes.rest.models.Category
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {

    private val client: APIClient = APIClient()

    private val categories: MutableLiveData<List<Category>> by lazy {
        MutableLiveData<List<Category>>().also {
            loadCategories(it)
        }
    }

    fun getCategories(): LiveData<List<Category>> {
        return categories
    }

    private fun loadCategories(liveData: MutableLiveData<List<Category>>) = viewModelScope.launch {
        liveData.value = client.getCategories()
    }
}