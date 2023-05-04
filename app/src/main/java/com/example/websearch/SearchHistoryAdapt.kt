//Made by Aaryan Kapoor and Matt Nova

package com.example.websearch

import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class SearchHistoryAdapt : RecyclerView.Adapter<SearchHistoryAdapt.SearchHistoryViewHolder>() {

    private var searchHistory: List<SearchHistoryEntity> = emptyList()


    fun setData(data: List<SearchHistoryEntity>) {
        searchHistory = data.subList(0,data.size)
            notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_history_item, parent, false)
        return SearchHistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchHistoryViewHolder, position: Int) {
        holder.bind(searchHistory[position])
    }

    override fun getItemCount(): Int {
        return searchHistory.size
    }

    inner class SearchHistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val searchQueryTextView: TextView = itemView.findViewById(R.id.searchQueryTextView)
        private val safeSearchTextView: TextView = itemView.findViewById(R.id.safeSearchTextView)
        private val timestampTextView: TextView = itemView.findViewById(R.id.timestampTextView)
        private val searchTypeTextView: TextView = itemView.findViewById(R.id.searchTypeTextView)

        fun bind(searchHistoryEntity: SearchHistoryEntity) {
            searchQueryTextView.text = searchHistoryEntity.searchQuery
            safeSearchTextView.text = if (searchHistoryEntity.safeSearch) "Yes" else "No"

            val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.getDefault())
            val date = Date(searchHistoryEntity.timeStamp)
            timestampTextView.text = dateFormat.format(date)

            searchTypeTextView.text = searchHistoryEntity.searchType
        }
    }
}
