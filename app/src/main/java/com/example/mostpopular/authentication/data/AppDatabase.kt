package com.example.mostpopular.authentication.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserModel::class], version = 8)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}