package com.example.blindquotes.rest;

import android.util.Log
import com.example.blindquotes.rest.models.Category
import com.example.blindquotes.rest.models.Quote
import io.ktor.client.HttpClient;
import io.ktor.client.engine.cio.CIO;
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

class APIClient {

    val client = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getQuote(): Quote {
         return client.get() {
            url("https://blind-quotes-api.herokuapp.com/all/random")
         }
    }

    suspend fun getQuotes(): List<Quote> {
        return client.get() {
            url("https://blind-quotes-api.herokuapp.com/all")
        }
    }

    suspend fun getCategories(): List<Category> {
        return client.get<List<Category>>() {
            url("https://blind-quotes-api.herokuapp.com/categories")
        }
    }
}
