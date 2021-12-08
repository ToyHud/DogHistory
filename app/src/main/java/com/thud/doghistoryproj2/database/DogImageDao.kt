package com.thud.doghistoryproj2.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import kotlinx.coroutines.flow.Flow
//import java.util.concurrent.Flow


@Dao   // used to write on the table & by view models to format query results for use in the UI
interface DogImageDao {

    @Query("SELECT * FROM DogImages")   // Queries to access the database
    fun getAllDogImages(): Flow<List<DogImageEntity>>

    @Query("SELECT * FROM DogImages ORDER BY id DESC LIMIT 1")   // get the most recent dog image (in descending order)
    fun getMostRecentlyAddedDog(): DogImageEntity

    @Query("DELETE from DogImages where id=(select max(id)-1 from DogImages)")   // used to delete from the database
    suspend fun deleteDog()

    @Insert
    suspend fun addDogImage(dogImageEntity: DogImageEntity)

}




