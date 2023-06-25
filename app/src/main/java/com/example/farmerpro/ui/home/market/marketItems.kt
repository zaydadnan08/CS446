import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun MarketplaceItemList(items: List<Item>) {
    val rows = items.chunked(2)
    Column(modifier = Modifier.fillMaxSize()) {
        for (row in rows) {
            Row(Modifier.fillMaxWidth()) {
                for (item in row) {
                    MarketplaceItemCard(item = item, modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun MarketplaceItemCard(item: Item, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .aspectRatio(1f)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.name,
                style = MaterialTheme.typography.h6
            )
            Text(
                text = item.price,
                style = MaterialTheme.typography.body1
            )
            Text(
                text = item.description,
                style = MaterialTheme.typography.body2
            )
        }
    }
}

data class Item(
    val name: String,
    val price: String,
    val description: String,
    val imageResId: Int
)
