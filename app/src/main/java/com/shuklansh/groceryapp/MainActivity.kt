package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.Screens.HomeScreen
import com.shuklansh.groceryapp.R
import com.shuklansh.groceryapp.RoomRepo.FavouriteEntity
import com.shuklansh.groceryapp.RoomRepo.FavouriteItemDatabase
import com.shuklansh.groceryapp.model.quickAddItem
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



//        GlobalScope.launch {
//            db.favouriteItemDao.insertFavItem(FavouriteEntity(itemName = "", itemImgLink = "", itemCount = 0, itemPrice = 0))
//        }

        supportActionBar?.hide()
        var navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        navController = navHostFragment.navController




    }


}