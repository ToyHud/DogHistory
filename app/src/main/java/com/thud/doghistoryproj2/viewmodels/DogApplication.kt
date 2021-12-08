package com.thud.doghistoryproj2.viewmodels

import android.app.Application
import com.thud.doghistoryproj2.database.AppDatabase

class DogApplication : Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }   // takes you to the AppDatabase
}