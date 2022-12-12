package com.thiagosouza.aluvery.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thiagosouza.aluvery.R
import com.thiagosouza.aluvery.models.Product
import com.thiagosouza.aluvery.sampledata.sampleProducts
import com.thiagosouza.aluvery.sampledata.sampleSections
import com.thiagosouza.aluvery.ui.components.CardProductItem
import com.thiagosouza.aluvery.ui.components.ProductsSection
import com.thiagosouza.aluvery.ui.components.SearchTextField

@Composable
fun HomeScreen(
    sections: Map<String, List<Product>>,
    searchTextValue: String = ""
) {
    var userSearchTextValue by remember { mutableStateOf(searchTextValue) }
    val searchedProducts = remember(userSearchTextValue) {
        sampleProducts.filter { product ->
            product.name.contains(
                userSearchTextValue,
                ignoreCase = true,
            ) || product.description?.contains(
                userSearchTextValue,
                ignoreCase = true,
            ) ?: false
        }
    }

    Column {
        SearchTextField(
            label = stringResource(id = R.string.search_label),
            placeholder = stringResource(id = R.string.search_placeholder),
            value = userSearchTextValue,
            onValueChanged = { newValue ->
                userSearchTextValue = newValue
            }
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            modifier = Modifier
                .fillMaxSize(),
        ) {
            if (userSearchTextValue.isBlank()) {
                for (section in sections) {
                    item {
                        val title = section.key
                        val products = section.value

                        ProductsSection(
                            title = title,
                            products = products
                        )
                    }
                }
            } else {
                items(searchedProducts) { product ->
                    CardProductItem(
                        product = product,
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                    )
                }
            }

        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(sampleSections)
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenWithSearchTextPreview() {
    HomeScreen(sampleSections, searchTextValue = "Lorem")
}