package com.example.blindquotes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.blindquotes.R
import com.example.blindquotes.rest.models.Category

class CategoryAdapter (private val categories: List<Category>, private val view: View) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>()
{
    // All element for each item in the list
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val category = itemView.findViewById<TextView>(R.id.category_content)
        val description = itemView.findViewById<TextView>(R.id.category_description)
        val guessBtn = itemView.findViewById<TextView>(R.id.button_guess)
        val completeBtn = itemView.findViewById<TextView>(R.id.button_complete)
    }

    // Initialise view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_category, parent, false)
        return ViewHolder(contactView)
    }

    // Bind a Category to the view
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val category: Category = categories[position]
        viewHolder.category.text = category.name
        viewHolder.description.text = category.description

        viewHolder.guessBtn.setOnClickListener { onGuessClick(category.path) }
        viewHolder.completeBtn.setOnClickListener { onCompleteClick(category.path) }
    }

    // Total count of items in the list
    override fun getItemCount(): Int {
        return categories.size
    }

    private fun onGuessClick(categoryName: String) {
        val bundle = bundleOf("category" to categoryName)
        view.findNavController().navigate(R.id.navigation_guess_container, bundle)
    }

    private fun onCompleteClick(categoryName: String) {
        val bundle = bundleOf("category" to categoryName)
        view.findNavController().navigate(R.id.navigation_complete_container, bundle)
    }
}