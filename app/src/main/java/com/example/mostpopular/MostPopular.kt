package com.example.mostpopular

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MostPopular :Application(){
    override fun onCreate() {
        super.onCreate()
    }
}