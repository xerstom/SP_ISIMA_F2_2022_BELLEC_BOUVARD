package com.example.blindquotes

class Quote(val content: String) {

    companion object {
        private var lastContactId = 0
        fun createQuoteList() : ArrayList<Quote> {
            val quotes = ArrayList<Quote>()
            quotes.add(Quote("item1 - cc"))
            quotes.add(Quote("item2 - bye bye"))
            quotes.add(Quote("item3"))
            quotes.add(Quote("item4"))
            quotes.add(Quote("item5"))
            quotes.add(Quote("item6"))
            // API CALL
            return quotes
        }
    }
}