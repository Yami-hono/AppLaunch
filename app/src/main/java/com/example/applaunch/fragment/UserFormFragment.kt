package com.example.applaunch.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.applaunch.R
import com.example.applaunch.database.User
import com.example.applaunch.model.MainViewModel
import com.example.applaunch.model.showToast


class UserFormFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_user_form, container, false)

        val save=view.findViewById<Button>(R.id.saveBtn)
        val cancel=view.findViewById<Button>(R.id.cancelBtn)
        val fName=view.findViewById<EditText>(R.id.firstName)
        val lName=view.findViewById<EditText>(R.id.lastName)
        val email=view.findViewById<EditText>(R.id.emailId)

        save.setOnClickListener {
            val userData= User(fName.text.toString(),
                lName.text.toString(),
                email.text.toString())
            if(email.text.toString()!="") {
                viewModel.insertIntoDB(userData)
                requireActivity().onBackPressed()
            }
            else
                showToast("Please Enter Email Address")
        }

        cancel.setOnClickListener {
            requireActivity().onBackPressed()
        }

        return view
    }
}