package com.thiagosouza.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thiagosouza.aluvery.sampledata.sampleProducts
import com.thiagosouza.aluvery.ui.components.ProductsSection

@Composable
fun HomeScreen() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
    ) {
        Spacer(Modifier)
        ProductsSection(title = "Promoções", products = sampleProducts)
        ProductsSection(title = "Doces", products = sampleProducts)
        ProductsSection(title = "Bebidas", products = sampleProducts)
        Spacer(Modifier)
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}