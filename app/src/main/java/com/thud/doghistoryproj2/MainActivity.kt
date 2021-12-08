package com.thud.doghistoryproj2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import com.squareup.picasso.Picasso
import com.thud.doghistoryproj2.database.DogImageEntity
import com.thud.doghistoryproj2.viewmodels.DogApplication
import com.thud.doghistoryproj2.viewmodels.MainActivity2
import com.thud.doghistoryproj2.viewmodels.MainViewModel
import com.thud.doghistoryproj2.viewmodels.MainViewModelFactory


class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels{
        MainViewModelFactory((application as DogApplication).database.dogImageDao())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val changeDogButton: Button = findViewById(R.id.button)
        val prevDogButton: Button = findViewById(R.id.prevButton)

        viewModel.currentlyDisplayedImage.observe(this, {
            val mainImage : ImageView = findViewById(R.id.DogImageHolder)
            Picasso.with(this).load(it.imgSrcUrl).into(mainImage)
        })

        changeDogButton.setOnClickListener {

            val currentImgUrl = viewModel.currentlyDisplayedImage.value?.imgSrcUrl
            val newDogImage = currentImgUrl?.let { it1-> DogImageEntity(imageUrl = it1) }

            viewModel.getNewDog()
            if (newDogImage !=null) {
                viewModel.addDog(newDogImage)
            }

            viewModel.deleteMostRecentDog()
        }

        prevDogButton.setOnClickListener {

            val intent = Intent( this, MainActivity2::class.java)
            startActivity(intent)
        }

    }
}