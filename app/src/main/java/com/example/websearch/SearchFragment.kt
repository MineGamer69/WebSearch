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
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope



class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchHistoryDao: SearchHistoryDAO
    private lateinit var safeSearchToggle: Switch
    private var safeSearchEnabled: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        searchHistoryDao = SearchHistoryDatabase.getDatabase(requireContext()).searchHistoryDao()

        safeSearchToggle = binding.safesearchtoggle
        safeSearchToggle.setOnCheckedChangeListener { _, isChecked ->
            safeSearchEnabled = isChecked
        }

        binding.button.setOnClickListener {
            val query = binding.searchInput.text.toString()
            val searchType = when (binding.searchTypeGroup.checkedRadioButtonId) {
                R.id.image_search_button -> SearchType.IMAGE
                R.id.news_search_button -> SearchType.NEWS
                R.id.web_search_button -> SearchType.WEB
                else -> SearchType.WEB
            }
            navigateToSearchResultFragment(searchType, query, safeSearchEnabled)
        }

        binding.button2.setOnClickListener {
            navigateToSearchHistoryFragment()
            showSearchHistory()
        }

        return binding.root
    }

    private fun navigateToSearchHistoryFragment() {
        findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToSearchHistoryFragment())
    }

    private fun navigateToSearchResultFragment(searchType: SearchType, query: String, safeSearchEnabled: Boolean) {
        val direction: NavDirections = when (searchType) {
            SearchType.IMAGE -> SearchFragmentDirections.actionSearchFragmentToImageSearchFragment(query, safeSearchEnabled)
            SearchType.NEWS -> SearchFragmentDirections.actionSearchFragmentToNewsSearchFragment(query, safeSearchEnabled)
            SearchType.WEB -> SearchFragmentDirections.actionSearchFragmentToWebSearchFragment(query, safeSearchEnabled)
        }
        findNavController().navigate(direction)

        lifecycleScope.launch {
            searchHistoryDao.insert(
                SearchHistoryEntity(
                    searchQuery = query,
                    safeSearch = safeSearchEnabled,
                    timeStamp = System.currentTimeMillis(),
                    searchType = searchType.toString()
                )
            )
        }
    }

    private fun showSearchHistory() {
        lifecycleScope.launch {
            val searchHistoryList = searchHistoryDao.getAll()
            searchHistoryList.forEach {
                Log.d(
                    "SearchHistory",
                    "ID: ${it.id}, Query: ${it.searchQuery}, SafeSearchEnabled: ${it.safeSearch}, Timestamp: ${it.timeStamp}, SearchType: ${it.searchType}"
                )
            }
        }
    }
}