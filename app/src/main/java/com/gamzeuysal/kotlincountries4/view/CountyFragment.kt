package com.gamzeuysal.kotlincountries4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gamzeuysal.kotlincountries4.R
import com.gamzeuysal.kotlincountries4.viewmodel.CountryViewModel
import kotlinx.android.synthetic.main.fragment_county.*


class CountyFragment : Fragment() {

    private var countryUuid = 0
    private lateinit var viewmodel:CountryViewModel

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

        //viewmodel initialize
        viewmodel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewmodel.getDataFromRoom()


        //arguman alma
        arguments?.let {
            countryUuid = CountyFragmentArgs.fromBundle(it).countryUuid
            println(countryUuid)
        }
        //live datayi view da observe edelim
        observeLiveDataFromRoom()

    }
    private fun observeLiveDataFromRoom()
    {
        viewmodel.countryLiveData.observe(viewLifecycleOwner, Observer {
         it?.let {
             countryName.text = it.countryName
             countryCapital.text = it.countryCapital
             countryRegion.text = it.countryRegion
             countryCurrency.text = it.countryCurrency
             countryLanguage.text = it.countryLanguage
         }
        })
    }
}