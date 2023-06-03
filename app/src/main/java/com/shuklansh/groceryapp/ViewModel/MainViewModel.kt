package com.shuklansh.groceryapp.ViewModel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.MainActivity
import com.shuklansh.groceryapp.RoomRepo.FavouriteEntity
import com.shuklansh.groceryapp.RoomRepo.FavouriteItemDatabase
import com.shuklansh.groceryapp.model.quickAddItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainViewModel : ViewModel() {


    val quickList = listOf<quickAddItem>(
        quickAddItem(
            "https://www.nicepng.com/png/full/263-2632160_kurkure-png.png",
            "Kurkure Masala Munch",
            20,
            0
        ),
        quickAddItem(

            "https://www.nicepng.com/png/full/76-765915_coca-cola-bottle-png.png",
            "CocaCola 1L",
            65,
            0
        ),
        quickAddItem(

            "https://www.nicepng.com/png/full/96-968723_ketchup-png.png",
            "Heinz Tomato Ketchup",
            80,
            0
        ),
        quickAddItem(

            "https://www.nicepng.com/png/full/81-812665_mcdonalds-fries-png.png",
            "Lays Spanish Tomato Tango",
            35,
            0
        ),
        quickAddItem(

            "https://www.nicepng.com/png/full/82-821839_jar-png.png",
            "Nescafe Coffee",
            150,
            0
        ),
        quickAddItem(

            "https://www.nicepng.com/png/full/832-8322594_amul-butter-png.png ",
            "Amul Buttermilk",
            66,
            0
        )
    )







    val _quickState = mutableStateListOf<quickAddItem>()
    val quickState : List<quickAddItem> = _quickState

    init {
        _quickState.addAll(quickList)
    }

    private fun increaseByOne(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                //Log.e("Tag","thread = ${Thread.currentThread().name}")
                _quickState[id].itemCount = quickState[id].itemCount.plus(1)
            } finally {
                //Log.e("Tag","cancelled $id")
            }
        }
    }

    private fun decreaseByOne(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                //Log.e("Tag","thread = ${Thread.currentThread().name}")
                _quickState[id].itemCount = quickState[id].itemCount.minus(1)
            } finally {
                //Log.e("Tag","cancelled $id")
            }
        }
    }

    private fun increaseBackgroundTask(incrOne :Int,id: Int) {
        //val delay = id + Random.nextInt(5)
        //Log.e("Tag","delay for id $id is $delay")
        //delay(delay * 1000L)
//        _quickState[id] = quickState[id].copy(
//            taskStatus = TaskStatus.COMPLETED
//        )
        quickState[id].itemCount = quickState[id].itemCount.plus(incrOne)
    }





//    val _kurkureCount = MutableStateFlow(0)
//    val kurkureCountState = _kurkureCount.asStateFlow()
//    val _CocaCola = MutableStateFlow(0)
//    val CocaColaState = _CocaCola.asStateFlow()
//    val _tomatoketchup = MutableStateFlow(0)
//    val tomatotState = _tomatoketchup.asStateFlow()
//    val _laystomato = MutableStateFlow(0)
//    val laystState = _laystomato.asStateFlow()
//    val _coffeenescafe = MutableStateFlow(0)
//    val cofftState = _coffeenescafe.asStateFlow()
//    val _amulbtmilk = MutableStateFlow(0)
//    val amultState = _amulbtmilk.asStateFlow()
//
//
//
//    fun increaseCount(inval : MutableStateFlow<Int>){
//
//        if(inval.value<10){
//            var ct = inval.value + 1
//            inval.value = ct
//        }
//
//    }
//    fun decreaseCount(inval : MutableStateFlow<Int>){
//
//        if(inval.value>0){
//            var ct = inval.value - 1
//            inval.value = ct
//        }
//
//    }

//    fun increaseKurkureCount(){
////        if(_kurkureCount.value<10) {
////            var count = _kurkureCount.value + 1
////            _kurkureCount.value = count
////        }
//        increaseCount(_kurkureCount)
//    }
//
//    fun decreaseKurkureCount(){
////        if(_kurkureCount.value>0) {
////            var count = _kurkureCount.value - 1
////            _kurkureCount.value = count
////        }
//        decreaseCount(_kurkureCount)
//    }



}