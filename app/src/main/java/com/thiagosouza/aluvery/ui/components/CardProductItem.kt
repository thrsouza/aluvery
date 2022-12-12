package com.thiagosouza.aluvery.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.thiagosouza.aluvery.R
import com.thiagosouza.aluvery.extensions.toBrazilianCurrency
import com.thiagosouza.aluvery.models.Product
import com.thiagosouza.aluvery.sampledata.sampleProducts
import com.thiagosouza.aluvery.ui.theme.AluveryTheme

@Composable
fun CardProductItem(
    product: Product,
    modifier: Modifier = Modifier
) {
    Card(
        modifier
            .fillMaxWidth()
            .heightIn(150.dp),
        elevation = 4.dp
    ) {
        Column {
            AsyncImage(
                model = product.image,
                contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                placeholder = painterResource(id = R.drawable.placeholder),
                contentScale = ContentScale.Crop
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.primaryVariant)
                    .padding(16.dp)
            ) {
                Text(
                    text = product.name
                )
                Text(
                    text = product.price.toBrazilianCurrency()
                )
            }
            product.description?.let {
                Text(
                    text = product.description,
                    Modifier
                        .padding(16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun CardProductItemPreview() {
    AluveryTheme {
        Surface {
            CardProductItem(
                product = sampleProducts.first { item -> item.description == null }
            )
        }
    }
}

@Preview
@Composable
private fun CardProductItemWithDescriptionPreview() {
    AluveryTheme {
        Surface {
            CardProductItem(
                product = sampleProducts.first { item -> item.description != null }
            )
        }
    }
}