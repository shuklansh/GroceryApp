package com.example.myapplication.Screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.viewModels
import coil.compose.AsyncImage
import com.example.myapplication.MainActivity
import com.shuklansh.groceryapp.R
import com.shuklansh.groceryapp.RoomRepo.FavouriteEntity
import com.shuklansh.groceryapp.RoomRepo.FavouriteItemDatabase
import com.shuklansh.groceryapp.ViewModel.MainViewModel


class AvOne : Fragment() {

    lateinit var imglinkl  : String
    //lateinit var listJson  : String

    var vm = viewModels<MainViewModel>()





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        arguments?.getString("imglink")?.let {
            imglinkl=it
        }


        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply { setContent {

//            var listJson by remember{ mutableStateOf("nill")}
//            arguments?.getString("bundleinList")?.let{
//                listJson = it
//            }

            var scrollstate = rememberScrollState()



            @Composable
            fun carouselItem(

                title: String = "",
                subtitle: String = "",
                imageLink: String

            ) {
                Card(
                    Modifier
                        .width(300.dp)
                        .padding(8.dp), shape = RoundedCornerShape(16.dp), elevation = 4.dp
                ) {
                    Box {
                        AsyncImage(
                            model = imageLink,
                            "aaa",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .drawWithCache {
                                    onDrawWithContent {
                                        drawContent()
                                        drawRect(
                                            Brush.verticalGradient(
                                                0.01f to Color.White.copy(alpha = 0F),
                                                100f to Color.Black
                                            )
                                        )
                                    }
                                }
                        )
                        Column(
                            Modifier
                                .padding(8.dp)
                                .fillMaxHeight(), verticalArrangement = Arrangement.Bottom
                        ) {
                            Text(text = title, fontSize = 14.sp, color = Color(0, 188, 212, 255))
                            Text(
                                text = subtitle,
                                fontSize = 22.sp,
                                color = Color(255, 255, 255, 255),
                                fontWeight = FontWeight.W500
                            )

                        }


                    }
                }
            }


            @Composable
            fun promotions() {
                LazyRow(
                    modifier = Modifier.height(130.dp), horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    item {
                        carouselItem(
                            title = "Fruits",
                            "50% OFF",
                            imageLink = "https://st4.depositphotos.com/1011549/19652/i/1600/depositphotos_196525964-stock-photo-collection-bright-fresh-fruits-vegetables.jpg"
                        )
                        carouselItem(
                            title = "Chips",
                            "BUY 3 GET 1",
                            imageLink = "https://mishry.com/wp-content/uploads/2022/08/best-masala-potato-chips-brands-in-india.jpg"
                        )
                        carouselItem(
                            title = "Safola Oil",
                            "20% OFF",
                            imageLink = "https://www.grabon.in/indulge/wp-content/uploads/2022/05/Best-Cooking-Oils.jpg"
                        )
                        carouselItem(
                            title = "Bed Sheets",
                            "BUY 2 GET 1",
                            imageLink = "https://images.woodenstreet.de/image/cache/data%2Fsalona-bichona%2Fgreen-printed-cotton-144-tc-double-bedsheet-set-with-two-pillow-covers%2Fupdated%2F1-750x650.jpg"
                        )
                        carouselItem(
                            title = "Nescafe Coffee",
                            "14% OFF",
                            imageLink = "https://www.nescafe.com/in/sites/default/files/styles/text_image_carousal_column_2_desktop/public/2022-06/NesCl_BR-banner_1042x540.jpg?h=7b5c2175&itok=88qCoSCD"
                        )
                    }
                }
            }



            @Composable
            fun box() {
                Box(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxHeight(0.3f)
                        .width(120.dp)
                        .clip(shape = RoundedCornerShape(40f))
                        .background(Color.Black)
                )
            }

            //Log.d("listaaaaa",listJson.toString())


            @Composable
            fun UI(modifier: Modifier = Modifier) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(86, 192, 168, 255))
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(8.dp))

                    ) {
//            Box(modifier = Modifier.padding(8.dp).align(Alignment.CenterStart).size(500.dp).clip(CircleShape).background(Color.Cyan))
                        Column {
                            Box(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth()
                                    .fillMaxHeight(0.46f)
                                    .background(Color.Green)
                            ){
                                AsyncImage(
                                    model = imglinkl,
                                    "aaa",
                                    contentScale = ContentScale.FillBounds,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(RoundedCornerShape(8.dp))
                                        .drawWithCache {
                                            onDrawWithContent {
                                                drawContent()
                                                drawRect(
                                                    Brush.verticalGradient(
                                                        0.01f to Color.White.copy(alpha = 0F),
                                                        100f to Color.Black
                                                    )
                                                )
                                            }
                                        }
                                )
                            }
                            //Spacer(Modifier.height(12.dp))
//                Row(
//                    Modifier
//                        .fillMaxWidth()
//                        .horizontalScroll(rememberScrollState())
//                ) {
//                    box()
//                    box()
//                    box()
//                    box()
//                    box()
//                    box()
//                    box()
//                    box()
//                    box()
//                }
                            promotions()
                            Box(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .fillMaxSize()
                                    .background(Color.Yellow)
                            ){

                            }
                        }
                    }

                }
            }

//            @Preview(showBackground = true)
//            @Composable
//            fun UIPreview() {
//
//                UI()
//
//            }





            // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                    UI()

                }
            }
        }
    }

}

