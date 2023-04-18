package com.example.websearch

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ImageSearchFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ImageSearchAdapt

    private lateinit var searchResultRecyclerView: RecyclerView
    private lateinit var searchResultAdapter: ImageSearchAdapt
    //sets up our createview
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //inflates our view
        return inflater.inflate(R.layout.fragment_image_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //sets up our layout with recycle view

        searchResultRecyclerView = view.findViewById(R.id.imageSearchResult)
        searchResultRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize the adapter
        searchResultAdapter = ImageSearchAdapt(emptyList())
        searchResultRecyclerView.adapter = searchResultAdapter
        //setup bundle from previous inputs and sets it to our variable
        val bundle = arguments
        val mN = bundle?.let { ImageSearchFragmentArgs.fromBundle(it).query.toString() }
        val safeSearchEnabled = bundle?.let { ImageSearchFragmentArgs.fromBundle(it).safeSearchEnabled }
        //val cou = bundle?.let { WebSearchFragmentArgs.fromBundle(it). }

        if (mN != null ) {
            //makes the api call
            if (safeSearchEnabled != null) {
                APIinterface.create(mN).imageSearchResponse(mN,1,10,true, safeSearchEnabled).enqueue(object : Callback<imageDataX> {
                    override fun onResponse(call: Call<imageDataX>, response: Response<imageDataX>) {
                        val searchResults = response.body()?.value ?: emptyList()
                        Log.d("works","working")
                        // Update the adapter with the new data
                        searchResultAdapter.updateData(searchResults)
                    }

                    //handling the on failure
                    override fun onFailure(call: Call<imageDataX>, t: Throwable) {
                        // Handle failure case
                        t.message?.let { Log.d("onFailure", it) }
                    }
                })
            }
        }
    }
}
