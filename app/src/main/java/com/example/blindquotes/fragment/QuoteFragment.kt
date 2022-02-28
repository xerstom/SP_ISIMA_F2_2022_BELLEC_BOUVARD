package com.example.blindquotes.fragment

import android.content.res.Resources
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
import com.example.blindquotes.viewModels.QuoteViewModel
import com.squareup.picasso.Picasso

class QuoteFragment : Fragment() {

    private lateinit var category: String
    private lateinit var content : TextView
    private lateinit var description : TextView
    private lateinit var title : TextView
    private lateinit var image  : ImageView
    private lateinit var resetButton : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        category = arguments?.getString("category") ?: "all"

        return inflater.inflate(R.layout.quote_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model: QuoteViewModel by viewModels()
        model.setCategory(category)

        content = view.findViewById<TextView>(R.id.quote_content)
        description = view.findViewById<TextView>(R.id.quote_description)
        title = view.findViewById<TextView>(R.id.quote_title)
        image = view.findViewById<ImageView>(R.id.quote_image)
        resetButton = view.findViewById<Button>(R.id.reset_button)
        resetButton.setOnClickListener { setQuotes(model) }

        setQuotes(model)
    }

    private fun setQuotes(model:QuoteViewModel){
        model.getQuote().observe(this.viewLifecycleOwner) { quote ->
            val res: Resources = resources
            description.text = res.getString(R.string.quote_descritpion, quote.character, quote.actor);
            content.text = quote.quote
            title.text = quote.title

            Picasso.get().load(quote.image).into(image);
        }
    }
}