package com.thud.doghistoryproj2.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DogImages") // table name within the database
data class DogImageEntity(
    @PrimaryKey(autoGenerate = true)  // primary key, will be auto-generated
    @NonNull  // permits us to access each row, if we choose
    @ColumnInfo(name = "id")  // defining a column in the database
    val id: Int = 0,  // integer value that begins at zero
    @ColumnInfo(name = "image_url")  // creating a second column with that name
    val imageUrl: String) {   // the image url column will take in data that is a string
}