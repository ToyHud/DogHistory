package com.thud.doghistoryproj2.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thud.doghistoryproj2.database.DogImageDao
import com.thud.doghistoryproj2.database.DogImageEntity
import com.thud.doghistoryproj2.network.DogImage
import java.lang.IllegalArgumentException

import androidx.lifecycle.*
import androidx.lifecycle.viewModelScope
import com.thud.doghistoryproj2.network.DogImageApi
import kotlinx.coroutines.launch



class MainViewModel (private val dogImageDao: DogImageDao) : ViewModel() {   // takes in a dogImageDao

    private val _currentlyDisplayedImage = MutableLiveData<DogImage>()   // can update image on screen
    val currentlyDisplayedImage: LiveData<DogImage> = _currentlyDisplayedImage

    init {   // will return a new dog
        getNewDog()
    }

    fun getNewDog() {
        viewModelScope.launch {
            // use a coroutine to retrieve the 1st item in the list
            _currentlyDisplayedImage.value = DogImageApi.retrofitService.getRandomDogImage()
        }
    }

    fun addDog(dogImageEntity: DogImageEntity) {  // adds a new dog image
        viewModelScope.launch {
            // use a coroutine to add a new dog
            dogImageDao.addDogImage(dogImageEntity)
        }
    }

    fun deleteMostRecentDog(){
        viewModelScope.launch {
            // use a coroutine to delete a dog
            dogImageDao.deleteDog()
        }

    }

    fun getAllDogs(): LiveData<List<DogImageEntity>> {   // getting all of the dog images
        return dogImageDao.getAllDogImages().asLiveData()
    }
}

class MainViewModelFactory(   // dog image dao can access the view model
    private val dogImageDao: DogImageDao
    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress( "UNCHECKED_CAST")
            return MainViewModel(dogImageDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


