package com.gamzeuysal.kotlincountries4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gamzeuysal.kotlincountries4.R


class CountyFragment : Fragment() {

    private var countryUuid = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_county, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //arguman alma
        arguments?.let {
            countryUuid = CountyFragmentArgs.fromBundle(it).countryUuid
            println(countryUuid)
        }
    }
}