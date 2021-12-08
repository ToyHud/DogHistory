package com.thud.doghistoryproj2.network

import com.squareup.moshi.Json

data class DogImage( // creating a data class 4 the dog images
    // val message: String,
    // log.src
    @Json(name = "message") val imgSrcUrl: String // we are taking the message & storing it in an image url
    // key is message, value is the string
)