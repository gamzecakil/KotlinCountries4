package com.gamzeuysal.kotlincountries4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.gamzeuysal.kotlincountries4.R
import com.gamzeuysal.kotlincountries4.adapter.CountryAdapter
import com.gamzeuysal.kotlincountries4.model.Country
import com.gamzeuysal.kotlincountries4.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : Fragment() {

    private lateinit var viewmodel : FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //view model initialize
        viewmodel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        //modelimizi dolduralım
        viewmodel.refreshData()

        //recyclerView adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = countryAdapter


        /*
        fragmentButton.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountyFragment()
            Navigation.findNavController(it).navigate(action)
        }
         */

     observeLiveData()
    }
    //verilerin geldiğini gözlemlecegiz
    fun observeLiveData()
    {
        viewmodel.countries.observe(viewLifecycleOwner, Observer {
                //it mutablelive datadan gelen veri --> Apı,database  vs. gelen veri
                //it in null gelmediğinden eminin doluysa let
                it?.let {
                //Array bos degilse recyclerView goster,error gösterme progressbarı  göster
                recyclerView.visibility = View.VISIBLE
                countryAdapter.updateCountryList(it)
                errorTextView.visibility = View.INVISIBLE
                progressBar.visibility = View.VISIBLE
            }
        })

        viewmodel.countryError.observe(viewLifecycleOwner, Observer {
            it?.let {
                if(it){
                   //it true ise yani it --> FeedViewModel'deki countryError   error varsa,
                    errorTextView.visibility = View.VISIBLE
                }else{
                    errorTextView.visibility =View.GONE
                }

            }
        })

        viewmodel.countryLoading.observe(viewLifecycleOwner, Observer {
            it?.let {
                if(it){
                    //it --> true loading varsa
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    errorTextView.visibility = View.GONE
                }
                else{
                    progressBar.visibility = View.GONE
                }
            }
        })
    }
}