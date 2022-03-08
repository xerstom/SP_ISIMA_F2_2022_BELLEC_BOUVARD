package com.example.blindquotes.rest.models;

import kotlinx.serialization.Serializable

@Serializable
class GuessQuote(val quote: String, val title: String, val actor: String, val character: String, val image: String, val ff: Boolean = true) {
}
