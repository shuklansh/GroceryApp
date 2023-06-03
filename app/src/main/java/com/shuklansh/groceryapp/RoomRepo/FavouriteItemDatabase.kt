package com.shuklansh.groceryapp.RoomRepo

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    version = 2,
    entities = [FavouriteEntity::class]
)
abstract class FavouriteItemDatabase : RoomDatabase() {

    abstract val favouriteItemDao: FavouriteItemDao

    companion object {
        @Volatile
        private var INSTANCE: FavouriteItemDatabase? = null

        fun getDatabase(context: Context): FavouriteItemDatabase {

            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context = context.applicationContext,
                        klass = FavouriteItemDatabase::class.java,
                        name = "favouriteDB"
                    ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
                }
            }
            return INSTANCE!!
        }


    }

}