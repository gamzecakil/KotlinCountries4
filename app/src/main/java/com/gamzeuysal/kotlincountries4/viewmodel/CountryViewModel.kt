package com.gamzeuysal.kotlincountries4.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gamzeuysal.kotlincountries4.model.Country

class CountryViewModel : ViewModel() {

    val countryLiveData = MutableLiveData<Country>()

    //tıklanan country itemını room database kaydedip oradan okuyup viewlara verecegiz.
    //dumy data
    fun getDataFromRoom(){
        val country = Country("Turkey","Asia","Ankara","TRY","Turkish","www.sss.com")
        //veri room da dumy olarak dolduguna göre live datayı tetikleyelim
        countryLiveData.value = country
    }
}