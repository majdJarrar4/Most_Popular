package com.example.mostpopular.authentication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_database")
data class UserModel(

    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    @ColumnInfo(name = "full_name")
    val fullName: String?,

    @ColumnInfo(name = "notional_id")
    val notionalID: String?,

    @ColumnInfo(name = "phone_number")
    val phoneNumber: String?,

    @ColumnInfo(name = "email")
    val email: String ,

//    @ColumnInfo(name = "user_email")
//    val email: String?,
    @ColumnInfo(name = "date_birth_day")
    val dateBirthDay: String?,

    @ColumnInfo(name = "password")
    val password: String?

)