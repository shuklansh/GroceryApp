package com.example.myapplication.Screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import coil.compose.AsyncImage
import com.shuklansh.groceryapp.R
import com.shuklansh.groceryapp.RoomRepo.FavouriteEntity
import com.shuklansh.groceryapp.RoomRepo.FavouriteItemDatabase
import com.shuklansh.groceryapp.ViewModel.MainViewModel
import com.shuklansh.groceryapp.model.quickAddItem
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class HomeScreen : Fragment() {

    lateinit var db: FavouriteItemDatabase
    var existStatus = false
    val vm = viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        db = FavouriteItemDatabase.getDatabase(requireActivity().applicationContext)


        var listInBundle: List<FavouriteEntity> = listOf()


        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home_screen, container, false)
        return ComposeView(requireContext()).apply {
            setContent {
                var scope = rememberCoroutineScope()
                val scrollState = rememberScrollState()
                var icval by remember {
                    mutableStateOf(0)
                }


                fun insertToDb(quickAddItem: quickAddItem, counts : Int) {
                    var name = quickAddItem.itemName
                    var link = quickAddItem.itemImgLink
                    var price = counts*quickAddItem.itemPrice
                    var count = counts
                    scope.launch {
                        db.favouriteItemDao.upsertFavItem(
                            FavouriteEntity(
                                itemName = name,
                                itemImgLink = link,
                                itemPrice = price,
                                itemCount = count
                            )
                        )
                        val data: Flow<List<FavouriteEntity>> = db.favouriteItemDao.getAllFavItems()
                        data.collect {
                            Log.d("favItemAdded", it.toString())
                        }
                    }
                }

                fun checkIfInDb(quickAddItem: String): Boolean {
                    var name = quickAddItem

//                                    GlobalScope.launch {
//                                        existStatus = db.favouriteItemDao.isRowIsExist(name)
//                                    }
                    if(db.favouriteItemDao.isRowIsExist(name)){
                        existStatus = true
                        return existStatus
                    }
                    return existStatus
                }

//                fun removeFromDb(quickAddItem: quickAddItem) {
//                    var name = quickAddItem.itemName
//                    var link = quickAddItem.itemImgLink
//                    var price = quickAddItem.itemPrice
//                    var count = quickAddItem.itemCount
//                    scope.launch {
//                        db.favouriteItemDao.deleteFavItem(
//                            FavouriteEntity(
//                                itemName = name,
//                                itemImgLink = link,
//                                itemPrice = price,
//                                itemCount = count
//                            )
//                        )
//                        val data2: Flow<List<FavouriteEntity>> =
//                            db.favouriteItemDao.getAllFavItems()
//                        data2.collect {
//                            Log.d("favItemRemoved", it.toString())
//                        }
//                    }
//                }


//                val initCountkurkure by vm.value.kurkureCountState.collectAsState()
//                val CocaCola by vm.value.CocaColaState.collectAsState()
//                val tomatoketchup by vm.value.tomatotState.collectAsState()
//                val laystomato by vm.value.laystState.collectAsState()
//                val coffeenescafe by vm.value.cofftState.collectAsState()
//                val amulbtmilk by vm.value.amultState.collectAsState()

//                val kk by vm.value.kurkureCountState.collectAsState()
//                val coca by vm.value.CocaColaState.collectAsState()
//                val tomatoketc by vm.value.tomatotState.collectAsState()
//                val laystom by vm.value.laystState.collectAsState()
//                val coffenes by vm.value.cofftState.collectAsState()
//                val amulbtm by vm.value.amultState.collectAsState()

//                val kk = vm.value._kurkureCount
//                val coca = vm.value._CocaCola
//                val tomatoketc = vm.value._tomatoketchup
//                val laystom = vm.value._laystomato
//                val coffenes = vm.value._coffeenescafe
//                val amulbtm = vm.value._amulbtmilk

//                val kk by remember {
//                    mutableStateOf(0)
//                }
//                val coca by remember {
//                    mutableStateOf(0)
//                }
//                val tomatoketc by remember {
//                    mutableStateOf(0)
//                }
//                val laystom by remember {
//                    mutableStateOf(0)
//                }
//                val coffenes by remember {
//                    mutableStateOf(0)
//                }
//                val amulbtm by remember {
//                    mutableStateOf(0)
//                }

//                val quickList = mutableListOf(
//                    quickAddItem(
//                        "https://www.nicepng.com/png/full/263-2632160_kurkure-png.png",
//                        "Kurkure Masala Munch",
//                        "20",
//                        0
//                    ),
//                    quickAddItem(
//                        "https://www.nicepng.com/png/full/76-765915_coca-cola-bottle-png.png",
//                        "CocaCola 1L",
//                        "65",
//                        0
//                    ),
//                    quickAddItem(
//                        "https://www.nicepng.com/png/full/96-968723_ketchup-png.png",
//                        "Heinz Tomato Ketchup",
//                        "80",
//                        0
//                    ),
//                    quickAddItem(
//                        "https://www.nicepng.com/png/full/81-812665_mcdonalds-fries-png.png",
//                        "Lays Spanish Tomato Tango",
//                        "35",
//                        0
//                    ),
//                    quickAddItem(
//                        "https://www.nicepng.com/png/full/82-821839_jar-png.png",
//                        "Nescafe Coffee",
//                        "150",
//                        0
//                    ),
//                    quickAddItem(
//                        "https://www.nicepng.com/png/full/832-8322594_amul-butter-png.png ",
//                        "Amul Buttermilk",
//                        "66",
//                        0
//                    ),
//                )


//                @Composable
//                fun circleIcon() {
//                    var scope = rememberCoroutineScope()
//                    Canvas(modifier = Modifier
//                        .size(100.dp)
//                        .padding(12.dp)
//                        .clickable {
//                            scope.launch {
//                                findNavController().navigate(R.id.action_homeScreen_to_avOne)
//                            }
//                        }) {
//                        drawCircle(
//                            color = Color.Cyan
//                        )
//                    }
//                }

                @Composable
                fun AppBar() {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.1f)
                            .background(Color(86, 191, 167, 255))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(bottomEnd = 12.dp, bottomStart = 12.dp))
                                .background(Color(8, 54, 44, 255)),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(vertical = 4.dp)
                                    .padding(8.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {

                                TextField(
                                    modifier = Modifier.fillMaxHeight(),
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Default.Search,
                                            contentDescription = "SearchIcon"
                                        )
                                    },
                                    colors = TextFieldDefaults.textFieldColors(
                                        textColor = Color(12, 12, 12, 255),
                                        disabledTextColor = Color.Transparent,
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent,
                                        disabledIndicatorColor = Color.Transparent,
                                        backgroundColor = Color(229, 222, 234, 255)
                                    ),
                                    value = "",
                                    onValueChange = {},
                                    label = {
                                        Text(
                                            text = "Search",
                                            fontSize = 12.sp,
                                            fontFamily = FontFamily.SansSerif
                                        )
                                    },
                                    singleLine = true,
                                    shape = RoundedCornerShape(8.dp),

                                    )

                                IconButton(modifier = Modifier.fillMaxHeight(), onClick = {
//                                    scope.launch {
//                                        listInBundle = getData()
//                                        val jsonList: String = Gson().toJson(listInBundle)
//
//                                        var bundle = Bundle()
//                                        bundle.putString("bundleinList",jsonList)
//                                        findNavController().navigate(R.id.action_homeScreen_to_avOne)
//                                    }

                                    findNavController().navigate(R.id.action_homeScreen_to_cartScreen)

                                }) {
                                    Icon(
                                        imageVector = Icons.Default.ShoppingCart,
                                        contentDescription = "Favs",
                                        tint = Color(
                                            230,
                                            223,
                                            235,
                                            255
                                        ),
                                    )
                                }

                                IconButton(modifier = Modifier.fillMaxHeight(), onClick = { }) {
                                    Icon(
                                        imageVector = Icons.Outlined.Notifications,
                                        contentDescription = "notifs",
                                        tint = Color(
                                            230,
                                            223,
                                            235,
                                            255
                                        )
                                    )
                                }


                            }
                        }
                    }

                }

                @Composable
                fun Dashboard() {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .fillMaxHeight(0.12f),
                        elevation = 4.dp,
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color(230, 223, 235, 255)),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                IconButton(onClick = {
                                    Toast.makeText(
                                        activity?.applicationContext,
                                        "account pressed",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }) {
                                    Icon(
                                        Icons.Filled.AccountBox,
                                        "",
                                        tint = Color(8, 54, 44, 255),
                                        modifier = Modifier.size(52.dp)
                                    )
                                }
                                Text(text = "Account")
                            }

                            Column(
                                modifier = Modifier
                                    .fillMaxHeight(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                IconButton(onClick = {
                                    Toast.makeText(
                                        activity?.applicationContext,
                                        "settings pressed",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }) {
                                    Icon(
                                        Icons.Rounded.Settings,
                                        "",
                                        tint = Color(8, 54, 44, 255),
                                        modifier = Modifier.size(52.dp)
                                    )
                                }
                                Text(text = "Settings")
                            }

                            Column(
                                modifier = Modifier
                                    .fillMaxHeight(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                IconButton(onClick = {
                                    Toast.makeText(
                                        activity?.applicationContext,
                                        "share pressed",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }) {
                                    Icon(
                                        Icons.Default.Share,
                                        "",
                                        tint = Color(8, 54, 44, 255),
                                        modifier = Modifier.size(52.dp)
                                    )
                                }
                                Text(text = "Share")
                            }



                            Column(
                                modifier = Modifier
                                    .fillMaxHeight(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                IconButton(onClick = {
                                    Toast.makeText(
                                        activity?.applicationContext,
                                        "delivery pressed",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }) {
                                    Icon(
                                        Icons.Filled.ShoppingCart,
                                        "",
                                        tint = Color(8, 54, 44, 255),
                                        modifier = Modifier.size(52.dp)
                                    )
                                }
                                Text(text = "Delivery")
                            }

                        }
                    }
                }


                @Composable
                fun carouselItem(

                    title: String = "",
                    subtitle: String = "",
                    imageLink: String

                ) {
                    Card(
                        Modifier
                            .width(230.dp)
                            .padding(8.dp), shape = RoundedCornerShape(8.dp), elevation = 4.dp
                    ) {
                        Box {
                            AsyncImage(
                                model = imageLink,
                                "aaa",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .clickable {
                                        scope.launch {
                                            var bundleInfo = Bundle()
                                            bundleInfo.putString("imglink", imageLink)
                                            findNavController().navigate(
                                                R.id.action_homeScreen_to_avOne,
                                                bundleInfo
                                            )

                                        }
                                    }
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
                                Text(
                                    text = title,
                                    fontSize = 14.sp,
                                    color = Color(255, 122, 122, 255)
                                )
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
                        modifier = Modifier.height(130.dp),
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
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
                fun categoryItem(
                    link: Int,
                    itemname: String
                ) {
                    Card(
                        modifier = Modifier
                            .height(200.dp)
                            .width(150.dp)
                            .padding(4.dp),
                        elevation = 8.dp,
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Box(
                            Modifier
                                .fillMaxSize()
                                .background(Color(0, 41, 33, 196)),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            Image(
                                modifier = Modifier
                                    .matchParentSize()
                                    .align(Alignment.TopCenter)
                                    .clip(RoundedCornerShape(8.dp))
                                    .drawWithCache {
                                        onDrawWithContent {
                                            drawContent()
                                            drawRect(
                                                Brush.verticalGradient(
                                                    0.3f to Color.White.copy(alpha = 0F),
                                                    100f to Color.Black
                                                )
                                            )
                                        }
                                    },
                                painter = painterResource(id = link),
                                contentScale = ContentScale.Inside,
                                contentDescription = "categories"
                            )
                            Column(
                                Modifier
                                    .fillMaxSize()

                            ) {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    Text(
                                        modifier = Modifier
                                            .align(Alignment.BottomCenter)
                                            .padding(12.dp),
                                        text = itemname, style = TextStyle(
                                            color = Color(
                                                204,
                                                255,
                                                245,
                                                255
                                            ), fontWeight = FontWeight.Bold, fontSize = 16.sp
                                        ), textAlign = TextAlign.Center
                                    )
                                    IconButton(
                                        modifier = Modifier.align(Alignment.TopEnd),
                                        onClick = { }) {
                                        Icon(
                                            imageVector = Icons.Default.KeyboardArrowRight,
                                            contentDescription = "",
                                            tint = Color(
                                                204,
                                                255,
                                                245,
                                                255
                                            )
                                        )
                                    }
                                }


                            }
                        }

                    }
                }


                @Composable
                fun categories() {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                            .padding(4.dp)
                    ) {
                        item {
                            categoryItem(itemname = "Soft Drinks", link = R.drawable.softdrink)
                            categoryItem(itemname = "Snacks", link = R.drawable.snack)
                            categoryItem(itemname = "Fruits and Veggies", link = R.drawable.veg)
                            categoryItem(itemname = "Cleaning essentials", link = R.drawable.clean)
                            categoryItem(itemname = "Cooking essentials", link = R.drawable.masala)


                        }


                    }
                }


                @SuppressLint("StateFlowValueCalledInComposition")
                @Composable
                fun quickAdd() {

                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.3f)
                            .padding(4.dp)
                    ) {

                        items(
                            items = vm.value.quickList
                        ) {
                            val (count, updateC) = rememberSaveable {
                                mutableStateOf(1)
                            }

                            var favourited by rememberSaveable { mutableStateOf(false) }




//                            var favourited by remember {
//                                mutableStateOf(checkIfInDb(it.itemName))
//                            }


                            Box(
                                Modifier
                                    .height(240.dp)
                                    .width(200.dp)
                                    .padding(4.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(Color(0, 41, 33, 196)),
                                contentAlignment = Alignment.BottomCenter
                            ) {
                                AsyncImage(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .align(Alignment.TopCenter)
                                        .drawWithCache {
                                            onDrawWithContent {
                                                drawContent()
                                                drawRect(
                                                    Brush.verticalGradient(
                                                        0.3f to Color.White.copy(alpha = 0F),
                                                        100f to Color.Black
                                                    )
                                                )
                                            }
                                        },
                                    model = it.itemImgLink,
                                    contentScale = ContentScale.Inside,
                                    contentDescription = "quickadd"
                                )









                                Column(
                                    Modifier
                                        .fillMaxSize()

                                ) {
                                    Box(modifier = Modifier.fillMaxSize()) {
                                        Text(
                                            modifier = Modifier
                                                .fillMaxWidth(0.52f)
                                                .align(Alignment.BottomStart)
                                                .padding(12.dp),
                                            text = it.itemName, style = TextStyle(
                                                color = Color(
                                                    204,
                                                    255,
                                                    245,
                                                    255
                                                ),
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 16.sp
                                            ), textAlign = TextAlign.Start
                                        )
                                        Text(
                                            modifier = Modifier
                                                .fillMaxWidth(0.48f)
                                                .align(Alignment.BottomEnd)
                                                .padding(12.dp),
//                                            (it.itemPrice*count).toString()
                                            text = (it.itemPrice * count).toString() + " ₹",
                                            style = TextStyle(
                                                color = Color(
                                                    204,
                                                    255,
                                                    245,
                                                    255
                                                ),
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 20.sp
                                            ),
                                            textAlign = TextAlign.Start
                                        )

                                        Row(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(8.dp))
                                                .background(Color(0, 0, 0, 255))
                                                .align(Alignment.TopEnd)
                                        ) {

                                            IconButton(onClick = {

//                                                if (favourited == false) {
//                                                    insertToDb(it,count)
//                                                    favourited = true
//                                                } else {
//                                                    removeFromDb(it)
//                                                    favourited = false
//                                                }
                                                insertToDb(it,count)

                                            }) {

//                                                if (favourited == true) {
//                                                    Icon(
//                                                        imageVector = Icons.Default.Favorite,
//                                                        contentDescription = "",
//                                                        tint = Color(
//                                                            255,
//                                                            40,
//                                                            40,
//                                                            255
//                                                        )
//                                                    )
//                                                } else {
//                                                    Icon(
//                                                        imageVector = Icons.Default.FavoriteBorder,
//                                                        contentDescription = "",
//                                                        tint = Color.White
//                                                    )
//                                                }

                                                    Icon(
                                                        imageVector = Icons.Default.Add,
                                                        contentDescription = "",
                                                        tint = Color(86, 191, 167, 255)
                                                    )


                                            }

                                            Text(
                                                modifier = Modifier.padding(
                                                    top = 8.dp,
                                                    start = 12.dp
                                                ), text = count.toString()
//                                                        count.toString()
//                                                icval.toString()
                                                , style = TextStyle(
                                                    textAlign = TextAlign.End,
                                                    fontSize = 22.sp,
                                                    color = Color(
                                                        204,
                                                        255,
                                                        245,
                                                        255
                                                    )
                                                )
                                            )
                                            Spacer(modifier = Modifier.width(12.dp))
                                            IconButton(
                                                onClick = {
//                                                    it.itemCount+=1
//                                                    icval = it.itemCount

                                                    if (count < 20) {
                                                        updateC(count + 1)
                                                    }

                                                }) {
                                                Icon(
                                                    imageVector = Icons.Default.KeyboardArrowUp,
                                                    contentDescription = "increase",
                                                    tint = Color(164, 252, 233, 255)
                                                )
                                            }
                                            IconButton(
                                                onClick = {
//                                                    it.itemCount-=1
//                                                    icval = it.itemCount
                                                    if (count > 1) {
                                                        updateC(count - 1)
                                                    }

                                                }) {
                                                Icon(
                                                    imageVector = Icons.Default.KeyboardArrowDown,
                                                    contentDescription = "decrease",
                                                    tint = Color(164, 252, 233, 255)
                                                )
                                            }


                                        }
                                    }


                                }
                            }

                        }
                    }


                }


                @Composable
                fun Content(paddingValues: PaddingValues) {


                    Column(
                        Modifier
                            .fillMaxSize()
                            .background(Color(86, 192, 168, 255))
                            .verticalScroll(scrollState),
                        verticalArrangement = Arrangement.Top
                    ) {

                        Dashboard()
                        Spacer(modifier = Modifier.fillMaxHeight(0.02f))
                        promotions()
                        Spacer(modifier = Modifier.fillMaxHeight(0.02f))
                        Box(modifier = Modifier.padding(start = 16.dp)) {
                            Text(
                                "Categories",
                                style = TextStyle(
                                    Color(0, 44, 35, 255),
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.Start
                                )
                            )
                        }
                        Spacer(modifier = Modifier.fillMaxHeight(0.02f))
                        categories()
                        Spacer(modifier = Modifier.fillMaxHeight(0.02f))
                        Box(modifier = Modifier.padding(start = 16.dp)) {
                            Text(
                                "Frequently Purchased",
                                style = TextStyle(
                                    Color(0, 44, 35, 255),
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.Start
                                )
                            )
                        }
                        Spacer(modifier = Modifier.fillMaxHeight(0.02f))
                        quickAdd()


                    }
                }

                @Composable
                fun LandingPageCompose() {
                    Scaffold(

                        topBar = { AppBar() },

                        ) { paddingValues ->
                        Content(paddingValues)

                    }
                }


                // A surface container using the 'background' color from the theme
//                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                LandingPageCompose()


//                    Column(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .background(Color.LightGray)
//                            .padding(12.dp)
//                    ) {
//                        Box(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .fillMaxHeight(0.13f)
//                                .clip(shape = RoundedCornerShape(40f))
//                                .background(Color.Magenta)
//                        ) {
////            Box(modifier = Modifier.padding(8.dp).align(Alignment.CenterStart).size(500.dp).clip(CircleShape).background(Color.Cyan))
//                            Row(
//                                Modifier
//                                    .fillMaxSize()
//                                    .horizontalScroll(rememberScrollState())
//                            ) {
//                                circleIcon()
//                                circleIcon()
//                                circleIcon()
//                                circleIcon()
//                                circleIcon()
//                                circleIcon()
//                                circleIcon()
//                                circleIcon()
//
//                            }
//                        }
//
//                    }
            }
        }
    }

}

//    @Composable
//    fun circleIcon() {
//        var scope = rememberCoroutineScope()
//        Canvas(modifier = Modifier
//            .size(100.dp)
//            .padding(12.dp)
//            .clickable {
//                scope.launch {
//                    findNavController().navigate(R.id.action_homeScreen_to_avOne)
//                }
//            }) {
//            drawCircle(
//                color = Color.Cyan
//            )
//        }
//    }
//}


//
//@Composable
//fun Greeting( modifier: Modifier = Modifier) {
//    Column(modifier= Modifier
//        .fillMaxSize()
//        .background(Color.LightGray)
//        .padding(12.dp) ) {
//        Box(modifier = Modifier
//            .fillMaxWidth()
//            .fillMaxHeight(0.13f)
//            .clip(shape = RoundedCornerShape(40f))
//            .background(Color.Magenta)){
////            Box(modifier = Modifier.padding(8.dp).align(Alignment.CenterStart).size(500.dp).clip(CircleShape).background(Color.Cyan))
//            Row(
//                Modifier
//                    .fillMaxSize()
//                    .horizontalScroll(rememberScrollState())) {
//                circleIcon()
//                circleIcon()
//                circleIcon()
//                circleIcon()
//                circleIcon()
//                circleIcon()
//                circleIcon()
//                circleIcon()
//
//            }
//        }
//
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MyApplicationTheme {
//        Greeting()
//    }
//}
//@Composable
//fun circleIcon(){
//    var scope = rememberCoroutineScope()
//    Canvas(modifier = Modifier
//        .size(100.dp)
//        .padding(12.dp)
//        .clickable {
//            scope.launch {
//                HomeScreen().findNavController().navigate(R.id.action_homeScreen_to_avOne)
//            }
//        }){
//        drawCircle(
//            color = Color.Cyan
//        )
//    }
//}


