package com.thiagosouza.aluvery.sampledata

import com.thiagosouza.aluvery.R
import com.thiagosouza.aluvery.models.Product
import java.math.BigDecimal


val sampleProducts = listOf(
    Product(
        name = "X-Salada Top",
        price = BigDecimal("14.99"),
        image = R.drawable.burger
    ),
    Product(
        name = "Pizza de Calabresa",
        price = BigDecimal("14.99"),
        image = R.drawable.pizza

    ),
    Product(
        name = "Batata Frita",
        price = BigDecimal("14.99"),
        image = R.drawable.fries
    )
)