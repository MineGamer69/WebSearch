//Made By Aaryan Kapoor & Matt Nova
package com.example.websearch

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ImageSearchFragment : Fragment() {
//set up variables
    private val viewModel: ImageSearchViewModel by activityViewModels()
    private lateinit var searchResultRecyclerView: RecyclerView
    private lateinit var searchResultAdapter: ImageSearchAdapt




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_image_search, container, false)
    }
//set up the view created
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        searchResultRecyclerView = view.findViewById(R.id.imageSearchResult)
        searchResultRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        searchResultAdapter = ImageSearchAdapt(emptyList())
        searchResultRecyclerView.adapter = searchResultAdapter
//using bundle to transfer args to other fragment
        val bundle = arguments
        val query = bundle?.let { ImageSearchFragmentArgs.fromBundle(it).query.toString() }
        val safeSearchEnabled = bundle?.let { ImageSearchFragmentArgs.fromBundle(it).safeSearchEnabled }

        if (query != null) {
            viewModel.performSearch(query, safeSearchEnabled ?: false)
        }

        viewModel.searchResults.observe(viewLifecycleOwner) { searchResults ->
            searchResultAdapter.updateData(searchResults)
        }
    }
}
