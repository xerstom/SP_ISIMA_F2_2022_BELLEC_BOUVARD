package com.example.blindquotes.fragment.guessQuote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.example.blindquotes.R
import com.example.blindquotes.viewModels.GuessQuoteViewModel

class GuessContainerFragment : Fragment() {

    private lateinit var category: String

    private var currentlyDisplayingAnswer: Boolean = true
    private lateinit var switchButton: Button

    private val inputFragment = GuessQuoteInputFragment()
    private val answerFragment = GuessQuoteAnswerFragment()

    private lateinit var bundle: Bundle

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        category = arguments?.getString("category") ?: "all"

        parentFragmentManager.commit {
            add(R.id.guess_fragment_container_view, inputFragment, "input")
            add(R.id.guess_fragment_container_view, answerFragment, "answer")
            hide(inputFragment)
            hide(answerFragment)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
        return inflater.inflate(R.layout.guess_container_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model: GuessQuoteViewModel by viewModels()
        model.setCategory(category)
        setupQuoteListener(model)

        switchButton = view.findViewById(R.id.guess_container_button)
        switchButton.setOnClickListener { onSwitchButton(model) }
    }

    private fun setupQuoteListener(model: GuessQuoteViewModel) {
        model.getQuote().observe(this.viewLifecycleOwner) { quote ->
            val description = resources.getString(R.string.quote_description, quote.character, quote.actor)

            bundle = Bundle()
            bundle.putString("content", quote.quote)
            bundle.putString("description", description)
            bundle.putString("title", quote.title)
            bundle.putString("image", quote.image)

            switchFragment()
        }
    }

    private fun onSwitchButton(model: GuessQuoteViewModel) {
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