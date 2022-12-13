package com.thiagosouza.aluvery.models

import androidx.annotation.DrawableRes
import java.math.BigDecimal

data class Product(
    val name: String,
    val price: BigDecimal,
    val description: String? = null,
    val image: String? = null,
)