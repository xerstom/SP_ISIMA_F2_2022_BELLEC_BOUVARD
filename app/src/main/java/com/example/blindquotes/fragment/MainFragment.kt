package com.example.blindquotes.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blindquotes.R
import com.example.blindquotes.adapter.CategoryAdapter
import com.example.blindquotes.rest.models.Category
import com.example.blindquotes.viewModels.CategoryViewModel

class MainFragment : Fragment() {

    private var recyclerView: RecyclerView? = null

    private lateinit var viewModel: CategoryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel = ViewModelProvider(this)[CategoryViewModel::class.java]

        val model: CategoryViewModel by viewModels()
        model.getCategories().observe(this.viewLifecycleOwner) { categories ->
            //Log.d("DEBUG - CATEGORIES", categories.map { c -> c.name }.joinToString());
            setupAdapter(categories)
        }

        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.quote_recyclerView)
        //setupAdapter()
    }

    // Create + fill adapter
    private fun setupAdapter(categories: List<Category> = Category.initDef()) {
        recyclerView?.adapter = CategoryAdapter(categories, requireView())
        recyclerView?.layoutManager = LinearLayoutManager(activity)
    }

}