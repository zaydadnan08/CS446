package com.example.farmerpro.ui.home.farmer.Analytics

import android.graphics.Typeface
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.animation.Crossfade
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.farmerpro.domain.model.Response
import com.example.farmerpro.domain.model.SaleRecord
import com.example.farmerpro.domain.model.SaleRecords
import com.example.farmerpro.ui.home.farmer.components.SalesRow
import com.example.farmerpro.ui.home.farmer.farmViewModel
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale
import kotlin.random.Random

@Composable
fun AnalyticsScreen (viewModel: farmViewModel = hiltViewModel(),  navController: NavController) {
    var selectedItem by remember { mutableStateOf("") }

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

    var d = pieEntries.map { (key, value) ->
        PieEntry( value.toFloat(), key, key)
    }

    var colors = List(d.size) { randomLightArgbColor() }

    val scrollState = rememberScrollState()

    Scaffold { padding ->
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                .verticalScroll(scrollState)
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

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (d.isNotEmpty()) {
                    Crossfade(targetState = d) { d ->
                        AndroidView(factory = { context ->
                            PieChart(context).apply {
                                layoutParams = LinearLayout.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    1024,
                                )
                                this.description.isEnabled = false
                                this.isDrawHoleEnabled = true
                                this.legend.isEnabled = false
                                this.setEntryLabelColor(R.color.black)

                                this.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                                    override fun onValueSelected(e: Entry?, h: Highlight?) {
                                        if (e != null) {
                                            selectedItem = e.data.toString()
                                        }
                                    }

                                    override fun onNothingSelected() {
                                        selectedItem = ""
                                    }
                                })

                                val ds = PieDataSet(d, "")
                                ds.colors = colors
                                ds.yValuePosition = PieDataSet.ValuePosition.INSIDE_SLICE
                                ds.xValuePosition = PieDataSet.ValuePosition.INSIDE_SLICE
                                ds.sliceSpace = 2f
                                ds.valueTextSize = 20f
                                ds.valueTypeface = Typeface.DEFAULT_BOLD

                                this.data = PieData(ds)
                            }
                        },
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(5.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = if (selectedItem == "") "All Sales" else "$selectedItem Sales",
                modifier = Modifier.padding(
                    bottom = 8.dp, top = 12.dp, start = 8.dp, end = 4.dp
                ),
                style = TextStyle(
                    fontWeight = FontWeight.Bold, fontSize = 28.sp, textAlign = TextAlign.Start
                )
            )
            if (d.isEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Nothing to see here",
                    style = TextStyle(
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            sales.sales.filter { sale ->
                sale.name.contains(selectedItem, ignoreCase = true)
            }.forEach { item ->
                SalesRow(
                    item = item,
                    viewModel = viewModel,
                )
                Spacer(modifier = Modifier.height(8.dp))
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
