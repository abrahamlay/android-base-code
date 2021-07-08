package com.atech.android.feature.test.favorite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.atech.android.MyApplication
import com.atech.android.R
import com.atech.android.base.util.SingleEvents
import com.atech.android.base.viewmodel.BaseViewModel
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.*
import javax.inject.Inject

@HiltViewModel
class TestFavoriteViewModel @Inject constructor(
    private val application: MyApplication,
    private val gson: Gson,
) : BaseViewModel(), FavoriteListener {

    private val _city = MutableLiveData<List<Addres>>()
    val city : LiveData<List<Addres>> = _city

    private val _district = MutableLiveData<List<Data>>()
    val district : LiveData<List<Data>> = _district

    private val _street = MutableLiveData<List<DataX>>()
    val street : LiveData<List<DataX>> = _street

    private val _needUpdateView = MutableLiveData<SingleEvents<Boolean>>()
    val needUpdateView : LiveData<SingleEvents<Boolean>> = _needUpdateView

    private val _citySelected = MutableLiveData<Int>()
    val citySelected : LiveData<Int> = _citySelected

    private val _districtSelected = MutableLiveData<Int>()
    val districtSelected : LiveData<Int> = _districtSelected

    fun init(){
        val inputStream: InputStream = application.resources.openRawResource(R.raw.address)
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        try {
            val reader: Reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
            var n: Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)
            }
        } finally {
            inputStream.close()
        }

        val jsonString: String = writer.toString()

        val temp = gson.fromJson(jsonString, ModelAddress::class.java)
        _city.value = temp.address
        _district.value = city.value?.get(0)?.data
        _street.value = district.value?.get(0)?.data

        _citySelected.value = 0
        _districtSelected.value = 0
    }

    override fun onStreetClick(index: Int) {

    }

    override fun onDistrictClick(index: Int) {
        _districtSelected.value = index
        _street.value = district.value?.get(index)?.data
        _needUpdateView.value = SingleEvents(true)
    }

    override fun onCityClick(index: Int) {
        _citySelected.value = index
        _district.value = city.value?.get(index)?.data
        onDistrictClick(0)
    }
}