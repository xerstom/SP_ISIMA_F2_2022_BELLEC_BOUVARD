package com.example.blindquotes.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blindquotes.Quote
import com.example.blindquotes.adapter.QuoteAdapter
import com.example.blindquotes.R
import com.example.blindquotes.viewModels.CategoryViewModel
import java.util.logging.Logger

class MainFragment : Fragment() {

    private var recyclerView: RecyclerView? = null

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: CategoryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById<RecyclerView>(R.id.quote_recyclerView)
        setupAdapter()
    }

    private fun setupAdapter() {
        val quotes = Quote.createQuoteList()
        // Create adapter passing in the sample user data
        // Attach the adapter to the recyclerview to populate items
        recyclerView?.adapter = QuoteAdapter(quotes)
        recyclerView?.layoutManager = LinearLayoutManager(activity)
    }

}