package com.shuklansh.groceryapp.Screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.viewModels
import coil.compose.AsyncImage
import com.shuklansh.groceryapp.R
import com.shuklansh.groceryapp.RoomRepo.FavouriteEntity
import com.shuklansh.groceryapp.RoomRepo.FavouriteItemDatabase
import com.shuklansh.groceryapp.ViewModel.MainViewModel
import com.shuklansh.groceryapp.model.quickAddItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class CartScreen : Fragment() {

    val vm by viewModels<MainViewModel>()
    lateinit var db: FavouriteItemDatabase


    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {

            setContent {

                db = FavouriteItemDatabase.getDatabase(requireContext().applicationContext)

                var scope = rememberCoroutineScope()
                var cartList: List<FavouriteEntity> by rememberSaveable {
                    mutableStateOf(emptyList())
                }

                fun removeFromDb(quickAddItem: FavouriteEntity) {
//                    var name = quickAddItem.itemName
//                    var link = quickAddItem.itemImgLink
//                    var price = quickAddItem.itemPrice
//                    var count = quickAddItem.itemCount
                    scope.launch {
                        db.favouriteItemDao.deleteFavItem(
                            quickAddItem
//                            FavouriteEntity(
//                                itemName = name,
//                                itemImgLink = link,
//                                itemPrice = price,
//                                itemCount = count
//                            )
                        )
                        val data2: Flow<List<FavouriteEntity>> =
                            db.favouriteItemDao.getAllFavItems()
                        data2.collect {
                            Log.d("favItemRemoved", it.toString())
                        }
                    }
                }

                scope.launch {
                    db.favouriteItemDao.getAllFavItems().collect {
                        cartList = it
                    }
                }

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0, 70, 63, 255))
                ) {
                    if (!cartList.isEmpty()) {

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color(0, 81, 92, 255))
                                .clip(
                                    RoundedCornerShape(8.dp)
                                )
                                .padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top

                        ) {

                            LazyColumn(contentPadding = PaddingValues(vertical = 8.dp)) {
                                items(cartList) {
                                    Box(
                                        Modifier
                                            .padding(4.dp)
                                            .fillMaxWidth()
                                            .height(330.dp)
                                            .clip(
                                                RoundedCornerShape(12.dp)
                                            )
                                            .background(Color(4, 211, 191, 255))) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(12.dp)
                                                ,
                                            horizontalAlignment = Alignment.Start,
                                            verticalArrangement = Arrangement.Center
                                        ) {

                                            Text(
                                                text = it.itemName,
                                                style = TextStyle(
                                                    fontSize = 22.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    letterSpacing = 2.sp
                                                )
                                            )
                                            Spacer(Modifier.height(8.dp))
                                            Text(
                                                text = "Price : " + it.itemPrice.toString() + " ₹",
                                                style = TextStyle(
                                                    fontSize = 16.sp,
                                                    fontWeight = FontWeight.Bold,
                                                )
                                            )
                                            Spacer(Modifier.height(8.dp))
                                            Text(
                                                text = "Quantity :" + it.itemCount.toString(),
                                                style = TextStyle(
                                                    fontSize = 16.sp,
                                                    fontWeight = FontWeight.Bold,
                                                )
                                            )
                                            Spacer(Modifier.height(8.dp))
                                            AsyncImage(
                                                modifier = Modifier
                                                    .height(120.dp)
                                                    .fillMaxWidth(),
                                                model = it.itemImgLink,
                                                contentDescription = "", contentScale = ContentScale.FillHeight
                                            )
                                            Spacer(Modifier.height(2.dp))
                                            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
                                                IconButton(onClick = { removeFromDb(it) }) {
                                                    Icon(
                                                        imageVector = Icons.Default.Delete,
                                                        tint = Color.Black,
                                                        contentDescription = ""
                                                    )
                                                }
                                            }

                                        }
                                    }


                                }
                            }
                        }


                    } else {

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color(0, 0, 0, 255))
                                .clip(
                                    RoundedCornerShape(8.dp)
                                )
                                .padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center

                        ) {

                            AsyncImage(
                                model = "https://www.ruuhbythebrandstore.com/images/cart_is_empty.png",
                                contentDescription = "",
                                contentScale = ContentScale.Fit
                            )


                        }

                    }

                }


            }

        }
    }


}