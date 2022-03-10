package com.example.blindquotes.fragment.completeQuote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.example.blindquotes.R
import com.example.blindquotes.viewModels.CompleteQuoteViewModel

class CompleteContainerFragment : Fragment() {

    private lateinit var category: String

    private var currentlyDisplayingAnswer: Boolean = true
    private lateinit var switchButton: Button

    private val inputFragment = CompleteQuoteInputFragment()
    private val answerFragment = CompleteQuoteAnswerFragment()

    private lateinit var bundle: Bundle

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        category = arguments?.getString("category") ?: "all"

        parentFragmentManager.commit {
            add(R.id.complete_fragment_container_view, inputFragment, "input")
            add(R.id.complete_fragment_container_view, answerFragment, "answer")
            hide(inputFragment)
            hide(answerFragment)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
        return inflater.inflate(R.layout.complete_container_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model: CompleteQuoteViewModel by viewModels()
        model.setCategory(category)
        setupQuoteListener(model)

        switchButton = view.findViewById(R.id.complete_container_button)
        switchButton.setOnClickListener { onSwitchButton(model) }
    }

    private fun setupQuoteListener(model: CompleteQuoteViewModel) {
        model.getQuote().observe(this.viewLifecycleOwner) { quote ->
            val partialContent = resources.getString(
                R.string.guess_quote,
                quote.partialQuote.first,
                " _".repeat(quote.partialQuote.missingWords),
                quote.partialQuote.second
            )

            val description = resources.getString(R.string.quote_description, quote.character, quote.actor)

            bundle = Bundle()
            bundle.putString("content", quote.quote)
            bundle.putString("partial", partialContent)
            bundle.putString("description", description)
            bundle.putString("title", quote.title)
            bundle.putString("image", quote.image)

            switchFragment()
        }
    }

    private fun onSwitchButton(model: CompleteQuoteViewModel) {
        if (currentlyDisplayingAnswer) {
            model.renew()
            switchButton.text = resources.getString(R.string.quote_container_button_answers)
        } else {
            switchButton.text = resources.getString(R.string.quote_container_button_another)
            switchFragment()
        }
    }

    private fun switchFragment() {
        if (!currentlyDisplayingAnswer) { // show answer
            answerFragment.arguments = bundle
            parentFragmentManager.commit {
                show(answerFragment)
                hide(inputFragment)
                setReorderingAllowed(true)
                addToBackStack(null)
            }

        } else { // show input
            inputFragment.arguments = bundle
            parentFragmentManager.commit {
                show(inputFragment)
                hide(answerFragment)
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }
        currentlyDisplayingAnswer = !currentlyDisplayingAnswer
    }
}
