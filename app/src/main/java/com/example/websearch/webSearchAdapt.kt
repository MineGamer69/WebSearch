package com.example.websearch

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.websearch.R

class webSearchAdapt(private var searchResults: List<com.example.websearch.Value>) :
    RecyclerView.Adapter<webSearchAdapt.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //inflates the layout
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.web_search_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val searchResult = searchResults[position]
        holder.bind(searchResult)
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //sets up variables for our recycle view
        private val iconImageView: ImageView = view.findViewById(R.id.iconImageView)
        private val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        private val platformTextView: TextView = view.findViewById(R.id.platformTextView)
        private lateinit var url: String

        fun bind(searchResult: com.example.websearch.Value) {
            //uses data binding
            Glide.with(itemView.context).load(searchResult.image.url).into(iconImageView)
            nameTextView.text = searchResult.title
            //platformTextView.text = searchResult.locations.joinToString { it.display_name }
            platformTextView.text = searchResult.description
            url = searchResult.url

            itemView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                itemView.context.startActivity(intent)
            }
        }
    }

    //get the size of the search results
    override fun getItemCount() = searchResults.size

    //Update data classes
    fun updateData(newData: List<com.example.websearch.Value>) {
        searchResults = newData
        notifyDataSetChanged()
    }
}