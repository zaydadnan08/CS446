package com.example.farmerpro.ui.home.farmer.Analytics

import android.graphics.ColorSpace.Rgb
import android.graphics.Typeface
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.farmerpro.R
import com.example.farmerpro.Screens
import com.example.farmerpro.components.AddFloatingActionButton
import com.example.farmerpro.components.SearchAppBar
import com.example.farmerpro.components.Title
import com.example.farmerpro.domain.model.InventoryItem
import com.example.farmerpro.domain.model.InventoryItems
import com.example.farmerpro.domain.model.Response
import com.example.farmerpro.domain.model.SaleRecord
import com.example.farmerpro.domain.model.SaleRecords
import com.example.farmerpro.ui.home.bottomBar.FarmerSubScreens
import com.example.farmerpro.ui.home.farmer.components.AddInventoryAlertDialog
import com.example.farmerpro.ui.home.farmer.components.ItemRow
import com.example.farmerpro.ui.home.farmer.farmViewModel
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale
import kotlin.random.Random

@Composable
fun AnalyticsScreen (viewModel: farmViewModel = hiltViewModel(),  navController: NavController) {
    var sales: SaleRecords = when(val salesResponse = viewModel.salesResponse) {
        is Response.Success -> salesResponse.data
        else -> {
            SaleRecords(emptyList<SaleRecord>())
        }
    }

    var revenue = sales.sales.sumOf { sale ->
        sale.price
    }

    var pieEntries = sales.sales.groupBy { it.name }
        .mapValues { entry -> entry.value.map { it.price }.reduce { acc, price -> acc + price } }

    Scaffold { padding ->
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp, top = 10.dp)
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Total Revenue",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 28.sp,
                            color = Color.Black
                        )
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
                        numberFormat.currency = Currency.getInstance("USD")

                        Text(
                            text = numberFormat.format(revenue),
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 48.sp,
                                color = Color.Black
                            )
                        )
                        Image(
                            modifier = Modifier.size(60.dp),
                            painter = painterResource(id = R.drawable.baseline_arrow_upward_24),
                            contentDescription = "Up",
                        )
                    }

                }

            }

            val d = pieEntries.map { (key, value) ->
                PieEntry( value.toFloat(), key)
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                AndroidView(factory = { context ->
                    PieChart(context).apply {
                        layoutParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            1024,
                        )
                        this.description.isEnabled = false
                        this.isDrawHoleEnabled = true
                        this.legend.isEnabled = true
                        this.legend.textSize = 14F
                        this.legend.horizontalAlignment =
                            Legend.LegendHorizontalAlignment.CENTER
                        this.setEntryLabelColor(R.color.black)

                        val ds = PieDataSet(d, "")
                        ds.colors = arrayListOf(
                            randomLightArgbColor(),
                            randomLightArgbColor(),
                            randomLightArgbColor(),
                            randomLightArgbColor()
                        )
                        ds.yValuePosition = PieDataSet.ValuePosition.INSIDE_SLICE
                        ds.xValuePosition = PieDataSet.ValuePosition.INSIDE_SLICE
                        ds.sliceSpace = 2f
                        ds.valueTextSize = 18f
                        ds.valueTypeface = Typeface.DEFAULT_BOLD

                        this.data = PieData(ds)
                    } },
                    // on below line we are specifying modifier
                    // for it and specifying padding to it.
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(5.dp)
                )
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                modifier = Modifier
                    .padding(1.dp)
                    .fillMaxSize()
            ) {
                sales.sales.forEach { sale ->
                    item {
                        Text(sale.name)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

fun randomLightArgbColor(): Int {
    val alpha = 255 // Fully opaque
    val red = Random.nextInt(156) + 100// Random value between 100 and 255
    val green = Random.nextInt(156) + 100
    val blue = Random.nextInt(156) + 100

    return (alpha shl 24) or (red shl 16) or (green shl 8) or blue
}