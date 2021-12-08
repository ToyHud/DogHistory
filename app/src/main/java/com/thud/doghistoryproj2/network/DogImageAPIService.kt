package com.thud.doghistoryproj2.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://dog.ceo/api/breeds/image/"

private val networkLoggingInterceptor =
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

private val moshi = Moshi.Builder() // breaks down the network reply into Json
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .client(OkHttpClient.Builder().addInterceptor(networkLoggingInterceptor).build())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface DogImageApiService {  // a way to access data from the web

    @GET( "random") //everytime we call this function, "random" causes it to add an image
    suspend fun getRandomDogImage(): DogImage //get endpoint

}

object DogImageApi {  // creates a singleton object, so we have access to the interface
    val retrofitService: DogImageApiService by lazy { retrofit.create(DogImageApiService::class.java) }
}  // by lazy instantiate only loads it, if you need it