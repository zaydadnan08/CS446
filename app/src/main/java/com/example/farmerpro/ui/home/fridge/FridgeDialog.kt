package com.example.farmerpro.ui.home.fridge


import android.location.Location
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.farmerpro.R
import com.example.farmerpro.components.ExpandableText
import com.example.farmerpro.components.LocationText
import com.example.farmerpro.domain.model.FridgeItem
import com.example.farmerpro.domain.model.FridgeRequest
import com.example.farmerpro.domain.model.MarketplaceItem
import com.example.farmerpro.domain.repository.Fridges

@Composable
fun FridgeDialog(
    closeDialog: () -> Unit,
    fridge: FridgeItem,
    owner: Boolean,
    deleteFridge: () -> Unit,
) {
    Dialog(onDismissRequest = closeDialog) {
        Surface(shape = RoundedCornerShape(20.dp), elevation = 24.dp) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = fridge.fridge_name,
                    modifier = Modifier.padding(horizontal = 12.dp),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Start,
                        color = Color.Black
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Location: ",
                        maxLines = 1,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Start,
                            color = Color.Black
                        )
                    )
                    LocationText(location = fridge.location.orEmpty())
                }

                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Contact Number: ",
                        maxLines = 1,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Start,
                            color = Color.Black
                        )
                    )
                    Text(
                        text = "${fridge.contact_number.orEmpty()}",
                        style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Normal),
                        maxLines = 1,
                    )
                }


                if(owner) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {
                            deleteFridge()
                            closeDialog()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFA80000)),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "Delete Posting",
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}