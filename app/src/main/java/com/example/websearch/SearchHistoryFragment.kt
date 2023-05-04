package com.example.websearch

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class SearchHistoryFragment : Fragment() {

    private lateinit var viewModel: SearchHistoryViewModel
    private lateinit var adapter: SearchHistoryAdapt



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(SearchHistoryViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated called")
        super.onViewCreated(view, savedInstanceState)

        // Initialize the RecyclerView and its adapter
        val recyclerView = view.findViewById<RecyclerView>(R.id.searchHistory)
        adapter = SearchHistoryAdapt()

        // Set the layout manager for the RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Set the adapter to the RecyclerView
        recyclerView.adapter = adapter

        // Find the clear button and set an OnClickListener
        val clearButton: Button = view.findViewById(R.id.clearButton)
        clearButton.setOnClickListener {
            viewModel.clearSearchHistory()
        }
            // Observe the searchHistory property and update the adapter when the data changes
            viewModel.searchHistory.observe(viewLifecycleOwner) { searchHistory ->
                Log.d("SearchHistoryFragment", "Search history changed: $searchHistory")

                adapter.setData(searchHistory)
            }
        }
    }

