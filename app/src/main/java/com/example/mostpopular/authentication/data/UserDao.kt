package com.example.mostpopular.authentication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun registerUser(users: UserModel)

    @Query("SELECT * FROM user_database WHERE user_database.email LIKE :email")
    fun loginUser(email:String): UserModel
}