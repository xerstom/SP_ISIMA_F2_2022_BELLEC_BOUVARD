package com.example.blindquotes.fragment

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

class GuessQuoteFragment : Fragment() {

    private lateinit var category: String
    private lateinit var content : TextView
    private lateinit var description : TextView
    private lateinit var title : TextView
    private lateinit var image  : ImageView
    private lateinit var resetButton : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        category = arguments?.getString("category") ?: "all"

        return inflater.inflate(R.layout.guess_quote_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model: GuessQuoteViewModel by viewModels()
        model.setCategory(category)

        content = view.findViewById(R.id.quote_content)
        description = view.findViewById(R.id.quote_description)
        title = view.findViewById(R.id.quote_title)
        image = view.findViewById(R.id.quote_image)
        resetButton = view.findViewById(R.id.reset_button)
        resetButton.setOnClickListener { renew(model) }

        setupQuoteListener(model)
    }

    private fun setupQuoteListener(model:GuessQuoteViewModel) {
        model.getQuote().observe(this.viewLifecycleOwner) { quote ->
            description.text = resources.getString(R.string.quote_description, quote.character, quote.actor);
            content.text = quote.quote
            title.text = quote.title

            Picasso.get().load(quote.image).into(image);
        }
    }

    private fun renew(model: GuessQuoteViewModel) {
        model.renew()
    }
}