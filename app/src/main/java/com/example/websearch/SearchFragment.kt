
//Made By Aaryan Kapoor & Matt Nova
package com.example.websearch

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.websearch.databinding.FragmentSearchBinding
import java.util.*

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchHistoryDatabase: SearchHistoryDatabase
    private lateinit var safeSearchToggle: Switch
    private var safeSearchEnabled: Boolean = false // Declare safeSearchEnabled

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        searchHistoryDatabase = SearchHistoryDatabase(requireContext())
        val db = searchHistoryDatabase.writableDatabase

        safeSearchToggle = binding.safesearchtoggle
        safeSearchToggle.setOnCheckedChangeListener { _, isChecked ->
            safeSearchEnabled = isChecked // Update the value of safeSearchEnabled when the switch is toggled
        }

        binding.button.setOnClickListener {
            val query = binding.searchInput.text.toString()
            val searchType = when (binding.searchTypeGroup.checkedRadioButtonId) {
                R.id.image_search_button -> SearchType.IMAGE
                R.id.news_search_button -> SearchType.NEWS
                R.id.web_search_button -> SearchType.WEB
                else -> SearchType.WEB // Default search type this runs only if the user does not pick an option.
            }
            navigateToSearchResultFragment(searchType, query, db, safeSearchEnabled) // Pass the value of safeSearchEnabled as a parameter
        }
        binding.button2.setOnClickListener {
            showSearchHistory()
        }

        return binding.root
    }

    private fun navigateToSearchResultFragment(searchType: SearchType, query: String, db: SQLiteDatabase, safeSearchEnabled: Boolean) {
        val direction: NavDirections = when (searchType) {
            SearchType.IMAGE -> SearchFragmentDirections.actionSearchFragmentToImageSearchFragment(query, safeSearchEnabled)
            SearchType.NEWS -> SearchFragmentDirections.actionSearchFragmentToNewsSearchFragment(query, safeSearchEnabled)
            SearchType.WEB -> SearchFragmentDirections.actionSearchFragmentToWebSearchFragment(query, safeSearchEnabled)
        }
        findNavController().navigate(direction)

        val currentTime = Calendar.getInstance().timeInMillis
        val values = ContentValues().apply {
            put(SearchHistoryDatabase.COLUMN_SEARCH_QUERY, query)
            put(SearchHistoryDatabase.COLUMN_SAFE_SEARCH, if (safeSearchEnabled) 1 else 0)
            put(SearchHistoryDatabase.COLUMN_TIMESTAMP, currentTime)
            put(SearchHistoryDatabase.COLUMN_SEARCH_TYPE, searchType.toString())
        }
        db.insert(SearchHistoryDatabase.TABLE_NAME, null, values)
    }
    private fun showSearchHistory() {
        val db = searchHistoryDatabase.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM ${SearchHistoryDatabase.TABLE_NAME}", null)

        val idIndex = cursor.getColumnIndex(SearchHistoryDatabase.COLUMN_ID)
        val queryIndex = cursor.getColumnIndex(SearchHistoryDatabase.COLUMN_SEARCH_QUERY)
        val safeSearchIndex = cursor.getColumnIndex(SearchHistoryDatabase.COLUMN_SAFE_SEARCH)
        val timestampIndex = cursor.getColumnIndex(SearchHistoryDatabase.COLUMN_TIMESTAMP)
        val searchTypeIndex = cursor.getColumnIndex(SearchHistoryDatabase.COLUMN_SEARCH_TYPE)

        if (idIndex >= 0 && queryIndex >= 0 && safeSearchIndex >= 0 && timestampIndex >= 0 && searchTypeIndex >= 0) {
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(idIndex)
                    val query = cursor.getString(queryIndex)
                    val safeSearchEnabled = cursor.getInt(safeSearchIndex) == 1
                    val timestamp = cursor.getString(timestampIndex)
                    val searchType = cursor.getString(searchTypeIndex)

                    Log.d("SearchHistory", "ID: $id, Query: $query, SafeSearchEnabled: $safeSearchEnabled, Timestamp: $timestamp, SearchType: $searchType")
                } while (cursor.moveToNext())
            }
        } else {
            Log.e("SearchHistory", "Column not found!")
        }


        cursor.close()
        //db.close()
    }


    override fun onDestroy() {
        super.onDestroy()
        searchHistoryDatabase.close()
    }
}
