package com.seinoindomobil.jetepod.presentation.dashboard

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

import com.seinoindomobil.jetepod.R
import com.seinoindomobil.jetepod.domain.model.*
import com.seinoindomobil.jetepod.presentation.theme.*
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.yield

@OptIn(ExperimentalPagerApi::class, FlowPreview::class)
@Composable
fun DashboardScreen(navController: NavController) {
    val items = DashboardSlider1.getData()
    val itemsBanner = dummyBannerDashboardItem
    val itemsKenalan = dummyKenalanDashboardItem

    val pagerState = rememberPagerState()
    var pageSize by remember { mutableStateOf(IntSize.Zero) }
    val lastIndex by remember(pagerState.currentPage) {
        derivedStateOf { pagerState.currentPage == items.size - 1 }
    }

    val pagerStateBanner = rememberPagerState()


    LaunchedEffect(Unit) {
        snapshotFlow { pagerState.currentPage }
            .debounce(2000L)
            .onEach {
                pagerState.animateScrollBy(
                    value = if (lastIndex) -(pageSize.width.toFloat() * items.size) else pageSize.width.toFloat(),
                    animationSpec = tween(if (lastIndex) 2000 else 1400)
                )
            }.launchIn(this)
    }

Column(
horizontalAlignment = Alignment.CenterHorizontally,
modifier = Modifier
.fillMaxSize()
.padding(12.dp)
.verticalScroll(rememberScrollState())
) {
    TopSectionDashboard()
    Spacer(modifier = Modifier.height(20.dp))
    Box {
        HorizontalPager(count = items.size, state = pagerState) { page ->
            AutomaticSliderCard(item = items[page], Modifier.onSizeChanged { pageSize = it })
        }
        Indicators(
            size = items.size,
            index = pagerState.currentPage,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
    FiturEpodLabel()
    FiturEpodList(list = dummyFiturDashboardItem, modifier = Modifier.padding(top = 10.dp))
//        BannerDashboardItemList(list = dummyBannerDashboardItem, modifier = Modifier.padding(top = 30.dp))
    HorizontalPager(
        count = itemsBanner.size,
        state = pagerStateBanner,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
    ) { page ->
        BannerDashboardItem(baner = itemsBanner[page])
    }
    HorizontalPagerIndicator(
        pagerState = pagerStateBanner, modifier = Modifier.padding(top = 10.dp)
    )
    KenalanDashboardItemList(list = itemsKenalan, modifier = Modifier.padding(top = 10.dp))
}
}

@Composable
fun TopSectionDashboard() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_logo_splash),
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column() {
            Text(
                text = "Hi,Aditya Septama",
                fontFamily = Poppins,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
            Text(text = "PT Exxon Mobil Tbk", fontFamily = Poppins, fontSize = 12.sp)
        }
        Image(
            painter = painterResource(id = R.drawable.ic_notification),
            contentDescription = null,
            alignment = Alignment.CenterEnd,
            modifier = Modifier
                .weight(2f)
                .padding(end = 20.dp)
        )
    }
}

@Composable
fun AutomaticSliderCard(item: DashboardSlider1, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_dashboard_slider1),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(y = -20.dp)
        ) {
            Column(horizontalAlignment = Alignment.Start) {
                Text(
                    text = item.title.toString(),
                    fontFamily = Poppins,
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
                Text(
                    text = "${item.numberCount}",
                    fontFamily = Poppins,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .offset(y = -10.dp)
                )
            }
            Image(
                painter = painterResource(id = item.image),
                contentDescription = null,
                modifier = Modifier.size(120.dp),
                alignment = Alignment.CenterEnd
            )
        }
    }
}

@Composable
fun BoxScope.Indicators(size: Int, index: Int, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .align(Alignment.Center)
            .offset(y = -30.dp)
    ) {
        repeat(size) {
            Indicator(isSelected = it == index)
        }
    }
}


@Composable
fun Indicator(isSelected: Boolean) {
    val width = animateDpAsState(
        targetValue = if (isSelected) 25.dp else 10.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )

    Box(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .height(5.dp)
            .width(50.dp)
            .clip(RectangleShape)
            .background(
                color = if (isSelected) Color.White else Grey
            )
    )
}

@Preview(showBackground = true)
@Composable
fun FiturEpodLabel() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp)
    ) {
        Text(
            text = "Fitur E-pod",
            fontFamily = Poppins,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Lihat Selengkapnya",
            fontFamily = Poppins,
            fontSize = 12.sp,
            fontWeight = FontWeight.Thin,
            color = Color(0xFF90A0D7)
        )
    }
}

@Composable
fun FiturEpodList(
    list: List<FiturDashboardItem>, modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 8.dp),
        modifier = modifier
    ) {
        items(list, key = { it.title!! }) { item ->
            FiturEpodItem(modifier, item)
        }
    }
}


@Composable
fun FiturEpodItem(
    modifier: Modifier = Modifier, fitur: FiturDashboardItem
) {
    Card(
        modifier = modifier
            .width(230.dp)
            .shadow(4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 18.dp,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Image(
                    painter = painterResource(id = fitur.image),
                    contentDescription = "${fitur.title}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(start = 8.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Column(modifier = Modifier.padding(start = 10.dp)) {
                    Text(text = "${fitur.title}", fontFamily = Poppins, fontSize = 14.sp)
                    Text(
                        text = "${fitur.subTitle}",
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = Poppins,
                        fontSize = 12.sp,
                        color = Color(0xFF90A0D7),
                    )
                }
            }
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Blue600),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(text = "${fitur.buttonTitle}", fontFamily = Poppins, color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_2)
@Composable
fun BannerDashboardItem(
    modifier: Modifier = Modifier, baner: BannerDashboardItem = BannerDashboardItem(
        "PT Seino Indomobil Logistics",
        "Loremmmmm Ipsum Loremmmmm Ipsum Loremmmmm Ipsum Loremmmmm Ipsum Loremmmmm Ipsum Loremmmmm Ipsum",
        R.drawable.img_banner1
    )
) {
    Row(
        modifier = Modifier
            .height(200.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(DarkBlue, DarkBlue100, DarkBlue200),
                    start = Offset.Zero,
                )
            ), verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier
                .padding(start = 8.dp)
                .width(200.dp),
        ) {
            Text(
                text = "${baner.title}",
                modifier = Modifier,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontFamily = Poppins,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "${baner.description}",
                modifier = Modifier,
                fontSize = 12.sp,
                fontWeight = FontWeight.Thin,
                color = Color.White,
                fontFamily = Poppins,
                maxLines = 3
            )
        }
        Column(
            modifier = Modifier
                .padding(end = 10.dp)
                .width(130.dp)
        ) {
            Image(
                painter = painterResource(id = baner.image),
                contentDescription = null,
                Modifier.size(170.dp),
                alignment = Alignment.CenterEnd,
                contentScale = ContentScale.FillBounds
            )
        }
    }
}


@Composable
fun KenalanDashboardItemList(
    list: List<KenalanDashboardItem>, modifier: Modifier = Modifier
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.height(400.dp)
    ) {
        item {
            Text(
                text = "Kenalan dengan E-Pod", style = TextStyle(
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start
                )
            )
        }
        items(list, key = { it.title!! }) { item ->
            KenalanDashboardItem(item)
            Divider(color = Color.Black, modifier = Modifier.padding(start = 16.dp, end = 16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KenalanDashboardItem(
    kenalan: KenalanDashboardItem = KenalanDashboardItem(
        "Buat Pesanan Pengiriman",
        "Semakin mudah untuk booking pengiriman, request pengiriman kapan saja dan dimana saja.",
        R.drawable.img_kenalan1
    )
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = kenalan.image),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
            Text(
                text = "${kenalan.title}", style = TextStyle(
                    color = Color.Black, fontSize = 14.sp, fontFamily = Poppins
                )
            )
            Text(
                text = "${kenalan.description}", style = TextStyle(
                    color = Color(0xFF5E5E5E),
                    fontSize = 12.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Thin
                )
            )
        }
    }
}


