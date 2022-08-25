package com.example.applaunch.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.applaunch.R
import com.example.applaunch.adapters.SwipeGesture
import com.example.applaunch.database.User
import com.example.applaunch.adapters.UserListAdapter
import com.example.applaunch.model.Call
import com.example.applaunch.model.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserListFragment : Fragment(), Call {

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    private lateinit var userListAdapter: UserListAdapter
    private lateinit var userSwipeGesture: SwipeGesture

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userListAdapter= UserListAdapter(this)
        userSwipeGesture=object: SwipeGesture(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                CoroutineScope(Dispatchers.IO).launch{
                    viewModel.database.contactDAO().delete(userListAdapter.data.get(viewHolder.absoluteAdapterPosition))
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_user_list, container, false)
        val addbtn=view.findViewById<ImageButton>(R.id.insertBtn)
        val listView=view.findViewById<RecyclerView>(R.id.userListRecyclerView)
        listView.adapter=userListAdapter
        val itemtouch=ItemTouchHelper(userSwipeGesture)
        itemtouch.attachToRecyclerView(listView)
        addbtn.setOnClickListener{
            findNavController().navigate(R.id.action_userListFragment_to_userFormFragment)
        }
        onBackPress()
        addObserver()
        return view
    }

    fun onBackPress(){
        val getSharedPref=context?.getSharedPreferences("USER_STATE",0)
        val state=getSharedPref?.getBoolean("LOGIN_STATE", false)
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if(state==true){
                        requireActivity().finish()
                    }
                    else
                        requireActivity().onBackPressed()
                }
            }
            )
    }

    private fun addObserver() {
        viewModel.database.contactDAO().getdata().observe(viewLifecycleOwner){
            userListAdapter.setUpdatedList(it as ArrayList<User> )

        }
    }

    override fun itemClick(firstName: String) {
        findNavController().navigate(R.id.action_userListFragment_to_weatherFragment)
    }
}