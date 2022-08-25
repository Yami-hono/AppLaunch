package com.example.applaunch.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserTable")
data class User(
    val fName:String,
    val lName:String,
    @PrimaryKey(autoGenerate = false)
    val emailId:String,

)
