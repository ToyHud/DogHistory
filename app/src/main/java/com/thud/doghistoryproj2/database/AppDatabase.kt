package com.thud.doghistoryproj2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [DogImageEntity::class], version = 1)   // creating a database of dogImage entities

abstract class AppDatabase: RoomDatabase() {   // create a database for the app to access
    abstract fun dogImageDao(): DogImageDao   // need an object to access the functions

    companion object {  // instantiated object of our class
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized( this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database")
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance

                instance

            }

        }

    }

}