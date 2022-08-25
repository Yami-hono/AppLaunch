package com.example.applaunch.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.applaunch.R
import com.example.applaunch.model.showToast
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_login, container, false)
        val mail = view.findViewById<EditText>(R.id.emailId)
        val pass = view.findViewById<EditText>(R.id.password)
        val button = view.findViewById<Button>(R.id.submitBtn)
        val sharedPref=context?.getSharedPreferences("USER_STATE",Context.MODE_PRIVATE)
        val editor=sharedPref?.edit()


        button.setOnClickListener {
            val email=sharedPref?.getString("DEFAULT_EMAIL","")
            val password=sharedPref?.getString("DEFAULT_PASSWORD","")
            if(mail.text==null) showToast("Please Enter an email address")
            else if(pass.text==null) showToast("Please Enter the password")

            else if(mail.text.toString()==email && pass.text.toString()==password){

                editor?.putBoolean("LOGIN_STATE", true)
                editor?.apply()

                findNavController().navigate(R.id.action_loginFragment_to_userListFragment)
            }
            else
                showToast("Please Enter Valid data")
        }

        return view
    }

}