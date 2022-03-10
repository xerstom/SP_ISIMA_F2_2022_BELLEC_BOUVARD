package com.example.blindquotes.fragment.completeQuote

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
import com.example.blindquotes.viewModels.CompleteQuoteViewModel
import com.squareup.picasso.Picasso

class CompleteQuoteAnswerFragment : Fragment() {

    private lateinit var content: TextView
    private lateinit var description: TextView
    private lateinit var title: TextView
    private lateinit var image: ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.complete_quote_answer_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        content = view.findViewById(R.id.complete_answer_quote_content)
        description = view.findViewById(R.id.complete_answer_quote_description)
        title = view.findViewById(R.id.complete_answer_quote_title)
        image = view.findViewById(R.id.complete_answer_quote_image)

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
        description.text = arguments?.getString("description") ?: "default"
        title.text =arguments?.getString("title") ?: "default"
        Picasso.get().load(arguments?.getString("image")).into(image);
    }

}