package com.shuklansh.groceryapp.model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class quickAddItem(
    //var id : Int,
    var itemImgLink : String,
    var itemName : String,
    var itemPrice : Long,
    var itemCount : Int
){}
