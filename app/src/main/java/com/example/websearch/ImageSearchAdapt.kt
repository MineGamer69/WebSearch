//Made by Aaryan Kapoor and Matt Nova

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

class ImageSearchAdapt(
    private var searchResults: List<com.example.websearch.ImageValue>,

) : RecyclerView.Adapter<ImageSearchAdapt.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_search_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val searchResult = searchResults[position]
        holder.bind(searchResult)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ImageView: ImageView = view.findViewById(R.id.imageView)
        private val imageTextView: TextView = view.findViewById(R.id.imageTextView)

        private lateinit var url: String

        fun bind(searchResult: ImageValue) {
            Glide.with(itemView.context).load(searchResult.url).into(ImageView)
            imageTextView.text = searchResult.title
            url = searchResult.url

            itemView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                itemView.context.startActivity(intent)

            }
        }
    }

    override fun getItemCount() = searchResults.size

    fun updateData(newData: List<com.example.websearch.ImageValue>) {
        searchResults = newData
        notifyDataSetChanged()
    }
}
