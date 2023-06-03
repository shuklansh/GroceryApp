package com.shuklansh.groceryapp.RoomRepo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavouriteEntity(

    //var id: Int? = null,
    var itemImgLink : String,
    @PrimaryKey
    var itemName : String,
    var itemPrice : Long,
    var itemCount : Int
)