package com.example.applaunch.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.applaunch.R
import com.example.applaunch.database.User
import com.example.applaunch.databinding.LiUserListItemBinding
import com.example.applaunch.model.Call


class UserListAdapter(var call: Call): RecyclerView.Adapter<UserListAdapter.UserViewHolder>(){

    var data=ArrayList<User>()

    fun setUpdatedList(data:ArrayList<User>){
        this.data= data
        notifyDataSetChanged()
    }


    inner class UserViewHolder(var binding: LiUserListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: User) {
            with(binding) {
                userData = item
                userCard.setOnClickListener {
                    item?.let {
                        call.itemClick(it.fName)
                    }
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = DataBindingUtil.inflate<LiUserListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.li_user_list_item,
            parent,
            false
        )
        return UserViewHolder(view)
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val searchResult = data.get(position)
        searchResult.let {
                searchResult->
            holder.bind(searchResult)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
