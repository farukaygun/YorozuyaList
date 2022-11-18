package com.farukaygun.yorozuyalist

import android.app.Application

class App : Application() {
    companion object {
        var isUserLoggedIn : Boolean = false
    }
}