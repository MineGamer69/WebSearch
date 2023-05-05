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

class WebSearchFragment : Fragment() {

    private val viewModel: WebSearchViewModel by activityViewModels()
    private lateinit var searchResultRecyclerView: RecyclerView
    private lateinit var searchResultAdapter: webSearchAdapt

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_web_search, container, false)
    }
//setting up the view creation for websearch
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchResultRecyclerView = view.findViewById(R.id.webSearchResult)
        searchResultRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        searchResultAdapter = webSearchAdapt(emptyList())
        searchResultRecyclerView.adapter = searchResultAdapter
//bundle data
        val bundle = arguments
        val query = bundle?.let { WebSearchFragmentArgs.fromBundle(it).query.toString() }
        val safeSearchEnabled = bundle?.let { WebSearchFragmentArgs.fromBundle(it).safeSearchEnabled }

        if (query != null) {
            viewModel.performSearch(query, safeSearchEnabled ?: false)
        }

        viewModel.searchResults.observe(viewLifecycleOwner) { searchResults ->
            searchResultAdapter.updateData(searchResults)
        }
    }
}