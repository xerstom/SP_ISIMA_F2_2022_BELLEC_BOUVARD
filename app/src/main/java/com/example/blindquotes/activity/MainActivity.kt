package com.example.blindquotes.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.QuoteSpan
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.blindquotes.R
import com.example.blindquotes.fragment.MainFragment
import com.example.blindquotes.rest.Quote
import com.example.blindquotes.viewModels.CategoryViewModel
import com.example.blindquotes.viewModels.QuoteViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }

        val model: CategoryViewModel by viewModels()
        val model2: QuoteViewModel by viewModels()
        model.getCategories().observe(this, Observer<List<String>>{ categories ->
            // update UI
            Log.d("DEBUG - CATEGORIES", categories.joinToString());
        })

        model2.getQuotes().observe(this, Observer<List<Quote>>{ quotes ->
            // update UI
            Log.d("DEBUG - CATEGORIES", quotes.map{ it.quote }.joinToString());
        })
    }
}