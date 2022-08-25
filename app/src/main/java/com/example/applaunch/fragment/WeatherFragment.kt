package com.example.applaunch.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.applaunch.R
import com.example.applaunch.model.MainViewModel

class WeatherFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getWeatherData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weather, container, false)

        val back = view.findViewById<ImageButton>(R.id.back)
        val logout = view.findViewById<Button>(R.id.logout)

        back.setOnClickListener {
            requireActivity().onBackPressed()
        }

        logout.setOnClickListener{
            val sharedPref=context?.getSharedPreferences("USER_STATE",0)
            val editor=sharedPref?.edit()
            editor?.putBoolean("LOGIN_STATE",false)
            editor?.apply()
            findNavController().navigate(R.id.action_weatherFragment_to_boardingFragment)
        }

        addObserver(view)
        return view
    }

    fun addObserver(view: View) {
        viewModel.weatherdata.observe(viewLifecycleOwner){
            val humidity=view.findViewById<TextView>(R.id.humidity)
            val temp=view.findViewById<TextView>(R.id.temp)
            val windSpeed=view.findViewById<TextView>(R.id.windSpeed)
            val status=view.findViewById<TextView>(R.id.weatherType)

            humidity.text=it.current?.humidity
            temp.text=it.current?.temp+"°F"
            windSpeed.text=it.current?.windSpeed
            status.text=it.current?.weatherDetail?.get(0)?.main


        }
    }

}