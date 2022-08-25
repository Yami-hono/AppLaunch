package com.example.applaunch

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.applaunch.database.UserDatabase
import com.example.applaunch.model.MainViewModel

lateinit var globalContext: Context
class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        val sharedPref=this.getSharedPreferences("USER_STATE",Context.MODE_PRIVATE)
        val editor=sharedPref.edit()

        val isUser=sharedPref.getBoolean("ALREADY_USER", false)

        if(!isUser){
           editor.putBoolean("ALREADY_USER",true)
           editor.putString("DEFAULT_EMAIL", "testapp@google.com")
            editor.putString("DEFAULT_PASSWORD","Test@123456")
        }
        editor.apply()

        globalContext=applicationContext
        viewModel.database= Room.databaseBuilder(applicationContext,
            UserDatabase::class.java,
            "UserTable")
            .build()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}