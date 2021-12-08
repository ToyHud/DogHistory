package com.thud.doghistoryproj2.viewmodels

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import com.squareup.picasso.Picasso
import androidx.appcompat.app.AppCompatActivity
import com.thud.doghistoryproj2.R
import com.thud.doghistoryproj2.viewmodels.MainViewModel
import com.thud.doghistoryproj2.viewmodels.MainViewModelFactory



class MainActivity2 : AppCompatActivity()  {
    private val viewModel: MainViewModel by viewModels{
        MainViewModelFactory((application as DogApplication).database.dogImageDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        viewModel.getAllDogs().observe(this){
            val imageView = findViewById<ImageView>(R.id.imageView2)
            Picasso.with(this).load(it.get(0).imageUrl).into(imageView)
            val button: Button = findViewById(R.id.button2)
            button.setOnClickListener {
                finish()
            }
        }
    }
}