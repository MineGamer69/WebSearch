//Made By Aaryan Kapoor & Matt Nova
package com.example.websearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NewsSearchFragment : Fragment() {

    private val viewModel: NewsSearchViewModel by activityViewModels()
    private lateinit var searchResultRecyclerView: RecyclerView
    private lateinit var searchResultAdapter: newsSearchAdapt

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_news_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchResultRecyclerView = view.findViewById(R.id.newsSearchResult)
        searchResultRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        searchResultAdapter = newsSearchAdapt(emptyList())
        searchResultRecyclerView.adapter = searchResultAdapter

        val bundle = arguments
        val query = bundle?.let { NewsSearchFragmentArgs.fromBundle(it).query.toString() }
        val safeSearchEnabled = bundle?.let { NewsSearchFragmentArgs.fromBundle(it).safeSearchEnabled }

        if (query != null) {
            viewModel.performSearch(query, safeSearchEnabled ?: false)
        }

        viewModel.searchResults.observe(viewLifecycleOwner) { searchResults ->
            searchResultAdapter.updateData(searchResults)
        }
    }
}
