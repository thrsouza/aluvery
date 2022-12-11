package com.thiagosouza.aluvery.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thiagosouza.aluvery.R
import com.thiagosouza.aluvery.extensions.toBrazilianCurrency
import com.thiagosouza.aluvery.models.Product
import com.thiagosouza.aluvery.ui.theme.Purple500
import com.thiagosouza.aluvery.ui.theme.Teal200
import java.math.BigDecimal


@Composable
fun ProductItem(product: Product) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .width(200.dp)
                .height(250.dp)

        ) {
            val imageSize = 100.dp

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Purple500, Teal200)
                        )
                    )
            ) {
                Image(
                    painter = painterResource(id = product.image),
                    contentDescription = null,
                    Modifier
                        .offset(y = imageSize / 2)
                        .size(size = imageSize)
                        .clip(shape = CircleShape)
                        .align(alignment = BottomCenter),
                    contentScale = ContentScale.Crop,
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = product.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = product.price.toBrazilianCurrency(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductItemPreview() {
    Box(
        modifier = Modifier.padding(8.dp)
    ) {
        ProductItem(
            Product(
                name = LoremIpsum(50).values.first(),
                price = BigDecimal("14.99"),
                image = R.drawable.placeholder
            )
        )
    }
}