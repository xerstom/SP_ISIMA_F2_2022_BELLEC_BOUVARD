package com.example.blindquotes.rest.models

import kotlinx.serialization.Serializable

@Serializable
data class Category(val name: String, val path: String, val description: String, val games: List<String>) {
    companion object {
        fun initDef(): List<Category> {
            return listOf()
        }
    }
}