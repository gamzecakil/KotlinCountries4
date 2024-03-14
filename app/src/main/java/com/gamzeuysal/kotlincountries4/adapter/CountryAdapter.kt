package com.gamzeuysal.kotlincountries4.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.gamzeuysal.kotlincountries4.R
import com.gamzeuysal.kotlincountries4.model.Country
import com.gamzeuysal.kotlincountries4.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.item_country.view.*

class CountryAdapter(val countyList :ArrayList<Country>) :RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(){

    class CountryViewHolder(var view: View):RecyclerView.ViewHolder(view){

    }
    //Layout Inflater
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_country,parent,false)
        return CountryViewHolder(view)
    }
   //RecyclerView's items
    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
         holder.view.name.text = countyList.get(position).countryName
         holder.view.region.text = countyList.get(position).countryRegion
       //tıklama deneme
        holder.view.setOnClickListener {
            //recyclerview da bir view tıklandıgında
         val action = FeedFragmentDirections.actionFeedFragmentToCountyFragment()
         Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
       return  countyList.size
    }
    //swiperefresh layout ile veriler güncellenirse bunu apaterinda bilmesi lazımki kendini güncelleyip gösterebilsin.
    fun updateCountryList(refreshlist : List<Country>)
    {
        countyList.clear()
        countyList.addAll(refreshlist)
        //adaptörü yenilemek için notifyDataSetChanged() kullanılıyor
        notifyDataSetChanged()

    }
}