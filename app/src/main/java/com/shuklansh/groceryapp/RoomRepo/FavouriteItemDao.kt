package com.shuklansh.groceryapp.RoomRepo

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteItemDao {

    @Upsert
    suspend fun upsertFavItem(favItem : FavouriteEntity)

    @Delete
    suspend fun deleteFavItem(favItem: FavouriteEntity)

    @Insert
    suspend fun insertFavItem(favItem: FavouriteEntity)

    @Query("SELECT * FROM FavouriteEntity ORDER BY itemName ASC")
    fun getAllFavItems() : Flow<List<FavouriteEntity>>

    @Query("SELECT EXISTS(SELECT * FROM FavouriteEntity WHERE itemName = :itname)")
    fun isRowIsExist(itname : String) : Boolean

}