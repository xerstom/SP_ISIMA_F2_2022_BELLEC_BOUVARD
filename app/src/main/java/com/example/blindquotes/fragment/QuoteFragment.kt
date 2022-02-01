package com.example.blindquotes.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.blindquotes.R

class QuoteFragment : Fragment() {

    companion object {
        fun newInstance() = QuoteFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

//        val model: CategoryViewModel by viewModels()
//        model.getCategories().observe(this.viewLifecycleOwner, Observer<List<Category>>{ categories ->
//            Log.d("DEBUG - CATEGORIES", categories.map { c -> c.name }.joinToString());
//            setupAdapter(categories)
//        })

        return inflater.inflate(R.layout.quote_fragment, container, false)
    }
}