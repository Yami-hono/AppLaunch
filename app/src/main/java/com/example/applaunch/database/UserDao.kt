package com.example.applaunch.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User)


    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM UserTable")
     fun getdata():LiveData<List<User>>
}