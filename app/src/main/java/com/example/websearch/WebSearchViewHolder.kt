package com.example.websearch

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView

import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide

class WebSearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val iconImageView: ImageView = itemView.findViewById(R.id.iconImageView)
    private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
    private val platformTextView: TextView = itemView.findViewById(R.id.platformTextView)
    //setup databinding
    fun bind(searchResult: com.example.websearch.Value) {
        Glide.with(itemView.context).load(searchResult.image.url).into(iconImageView)
        nameTextView.text = searchResult.title
        Log.d("works", searchResult.image.url)
        //platformTextView.text = searchResult.locations.joinToString { it.display_name }
        platformTextView.text = searchResult.description
        itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(searchResult.url))
            itemView.context.startActivity(intent)
        }

    }
}