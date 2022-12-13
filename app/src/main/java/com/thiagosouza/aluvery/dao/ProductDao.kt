package com.thiagosouza.aluvery.dao

import androidx.compose.runtime.mutableStateListOf
import com.thiagosouza.aluvery.models.Product

class ProductDao {
    companion object {
        private val products = mutableStateListOf<Product>()
    }

    fun products() = products.toList()

    fun save(product: Product) {
        products.add(product)
    }
}