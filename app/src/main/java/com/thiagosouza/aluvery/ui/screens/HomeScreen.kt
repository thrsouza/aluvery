package com.thiagosouza.aluvery.ui.screens

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
import com.thiagosouza.aluvery.sampledata.sampleCandies
import com.thiagosouza.aluvery.sampledata.sampleDrinks
import com.thiagosouza.aluvery.sampledata.sampleProducts
import com.thiagosouza.aluvery.sampledata.sampleSections
import com.thiagosouza.aluvery.ui.components.CardProductItem
import com.thiagosouza.aluvery.ui.components.ProductsSection
import com.thiagosouza.aluvery.ui.components.SearchTextField

class HomeScreenUiState(
    val sections: Map<String, List<Product>> = emptyMap(),
    val searchedProducts: List<Product> = emptyList(),
    val searchValue: String = "",
    val onSearchValueChange: (String) -> Unit = {}
) {
    fun isShowSections(): Boolean {
        return searchValue.isBlank()
    }
}

@Composable
fun HomeScreen(products: List<Product>) {
    val sections = mapOf(
        stringResource(id = R.string.section_all_products) to products,
        stringResource(id = R.string.section_offers) to sampleDrinks + sampleCandies,
        stringResource(id = R.string.section_candies) to sampleCandies,
        stringResource(id = R.string.section_drinks) to sampleDrinks,
    )

    var searchValue by remember { mutableStateOf("") }

    fun containsInNameOrDescription() = { product: Product ->
        product.name.contains(
            searchValue,
            ignoreCase = true,
        ) || product.description?.contains(
            searchValue,
            ignoreCase = true,
        ) ?: false
    }

    val searchedProducts = remember(products, searchValue) {
        if (searchValue.isNotBlank()) {
            sampleProducts.filter(containsInNameOrDescription())
        } else {
            emptyList()
        } + products.filter(containsInNameOrDescription())
    }

    val homeScreenUiState = remember(products, searchValue) {
        HomeScreenUiState(
            sections = sections,
            searchValue = searchValue,
            onSearchValueChange = { newValue ->
                searchValue = newValue
            },
            searchedProducts = searchedProducts,
        )
    }

    HomeScreen(state = homeScreenUiState)
}

@Composable
fun HomeScreen(
    state: HomeScreenUiState = HomeScreenUiState(),
) {
    val sections = state.sections
    val searchValue = state.searchValue
    val searchedProducts = state.searchedProducts

    Column {
        SearchTextField(
            label = stringResource(id = R.string.search_label),
            placeholder = stringResource(id = R.string.search_placeholder),
            value = searchValue,
            onValueChanged = state.onSearchValueChange
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            modifier = Modifier
                .fillMaxSize(),
        ) {
            if (state.isShowSections()) {
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
    HomeScreen(
        HomeScreenUiState(sections = sampleSections)
    )
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenWithSearchTextPreview() {
    HomeScreen(
        HomeScreenUiState(
            sections = sampleSections,
            searchValue = "Lorem",
        )
    )
}