package com.example.websearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.websearch.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        //binding.lifecycleOwner = viewLifecycleOwner // Set the lifecycle owner for the binding

        binding.button.setOnClickListener {
            val query = binding.searchInput.text.toString()
            val searchType = when (binding.searchTypeGroup.checkedRadioButtonId) {
                R.id.image_search_button -> SearchType.IMAGE
                R.id.news_search_button -> SearchType.NEWS
                R.id.web_search_button -> SearchType.WEB
                else -> SearchType.WEB// Default search type
            }
            navigateToSearchResultFragment(searchType, query)
        }

        return binding.root
    }

    private fun navigateToSearchResultFragment(searchType: SearchType, query: String) {
        val direction: NavDirections = when (searchType) {
            SearchType.IMAGE -> SearchFragmentDirections.actionSearchFragmentToImageSearchFragment(query)
            SearchType.NEWS -> SearchFragmentDirections.actionSearchFragmentToNewsSearchFragment(query)
            SearchType.WEB -> SearchFragmentDirections.actionSearchFragmentToWebSearchFragment(query)
        }
        findNavController().navigate(direction)
    }


}
