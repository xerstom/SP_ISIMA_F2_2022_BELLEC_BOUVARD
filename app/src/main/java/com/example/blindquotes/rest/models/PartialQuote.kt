package com.example.blindquotes.rest.models

import kotlinx.serialization.Serializable

@Serializable
class PartialQuote(val first: String, val second: String, val missingWords: Int) {
}