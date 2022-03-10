package com.example.blindquotes.fragment.guessQuote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.blindquotes.R
import com.example.blindquotes.viewModels.GuessQuoteViewModel
import com.squareup.picasso.Picasso

class GuessQuoteInputFragment : Fragment() {

    private lateinit var content : TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.guess_quote_input_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        content = view.findViewById(R.id.guess_input_quote_content)

        setView()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            setView()
        }
    }

    private fun setView() {
        content.text = arguments?.getString("content") ?: "default"
    }
}