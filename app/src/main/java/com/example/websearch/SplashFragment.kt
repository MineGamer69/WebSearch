package com.example.websearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation


class SplashFragment : Fragment(), View.OnClickListener {
    lateinit var navCtrl: NavController



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navCtrl = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.start).setOnClickListener(this)
    }
    override fun onClick(p0: View?){
        val action = SplashFragmentDirections.actionSplashFragmentToSearchFragment()
        navCtrl.navigate(action)
    }



}