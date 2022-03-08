package com.example.blindquotes.rest;

import com.example.blindquotes.rest.models.Category
import com.example.blindquotes.rest.models.CompleteQuote
import com.example.blindquotes.rest.models.GuessQuote
import io.ktor.client.HttpClient;
import io.ktor.client.engine.cio.CIO;
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

class APIClient {

    private val client = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getGuessQuote(category: String): GuessQuote {
         return client.get() {
            url("https://blind-quotes-api.herokuapp.com/$category/random")
         }
    }

    suspend fun getCompleteQuote(category: String): CompleteQuote {
         return client.get() {
            url("https://blind-quotes-api.herokuapp.com/$category/complete")
         }
    }

    suspend fun getQuotes(): List<GuessQuote> {
        return client.get() {
            url("https://blind-quotes-api.herokuapp.com/all")
        }
    }

    suspend fun getCategories(): List<Category> {
        return client.get() {
            url("https://blind-quotes-api.herokuapp.com/categories")
        }
    }
}
