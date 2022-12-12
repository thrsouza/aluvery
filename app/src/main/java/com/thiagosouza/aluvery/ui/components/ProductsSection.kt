package com.thiagosouza.aluvery.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thiagosouza.aluvery.models.Product
import com.thiagosouza.aluvery.sampledata.sampleProducts

@Composable
fun ProductsSection(
    title: String,
    products: List<Product>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight(400),
            lineHeight = 24.2.sp,
            modifier = Modifier.padding(start = 16.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        ) {
            items(products) { product ->
                ProductItem(product = product)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductsSectionPreview() {
    Box(modifier = Modifier.padding(vertical = 16.dp)) {
        ProductsSection(
            title = "Promoções",
            products = sampleProducts
        )
    }
}