package com.example.blindquotes.rest;

import kotlinx.serialization.Serializable

@Serializable
class Quote(val quote: String, val title: String, val actor: String, val character: String, val image: String, val ff: Boolean) {

}
