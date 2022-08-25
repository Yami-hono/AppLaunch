package com.example.applaunch.model

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applaunch.database.User
import com.example.applaunch.database.UserDatabase
import com.example.applaunch.globalContext
import com.example.applaunch.network.NetworkService
import com.example.applaunch.network.RetrofitInstance
import com.example.applaunch.repository.WeatherResponse
import kotlinx.coroutines.launch
import kotlin.Exception

class MainViewModel: ViewModel() {

    val weatherdata = MutableLiveData<WeatherResponse>()
    lateinit var database: UserDatabase

    fun getWeatherData(){
        viewModelScope.launch{
            try {
                val retrofitInstance= RetrofitInstance.getRetrofitInstance().create(NetworkService::class.java)
                val res=retrofitInstance.getWeatherInfo()
                if(res.isSuccessful){
                    weatherdata.value=res.body()
                }
                else{
                    showToast("something went wrong")
                }
            }catch (ex:Exception){
                showToast("something went wrong, try again")
            }
        }
    }


    fun insertIntoDB(user: User){
        viewModelScope.launch {
            try {
                database.contactDAO().insert(user)
            }catch (ex:Exception){
                showToast("Something went wrong")
            }
        }
    }

}

fun showToast(text:String){
    Toast.makeText(globalContext, text,Toast.LENGTH_SHORT).show()
}


interface Call {
    fun itemClick(firstName: String)
}
