package com.example.applaunch.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.applaunch.R

class BoardingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_boarding, container, false)
        val getSharedPref=context?.getSharedPreferences("USER_STATE",0)
        val state=getSharedPref?.getBoolean("LOGIN_STATE", false)
        view.findViewById<TextView>(R.id.loginBtn).setOnClickListener{
            if(state == true)
            {
                findNavController().navigate(R.id.action_boardingFragment_to_userListFragment)
            }
            else
                findNavController().navigate(R.id.action_boardingFragment_to_loginFragment)
        }

        return view
    }

}