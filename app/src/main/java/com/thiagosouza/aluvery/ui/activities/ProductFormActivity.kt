package com.thiagosouza.aluvery.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.thiagosouza.aluvery.R
import com.thiagosouza.aluvery.dao.ProductDao
import com.thiagosouza.aluvery.models.Product
import com.thiagosouza.aluvery.ui.theme.AluveryTheme
import java.math.BigDecimal
import java.text.DecimalFormat

class ProductFormActivity : ComponentActivity() {
    private val productDao = ProductDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                Surface {
                    ProductFormScreen(
                        onSaveClick = { product ->
                            productDao.save(product)
                            finish()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ProductFormScreen(
    onSaveClick: (Product) -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(Modifier)

        Text(
            text = stringResource(id = R.string.creating_product),
            fontSize = 28.sp,
            modifier = Modifier
                .fillMaxWidth(),
        )

        var imageUrl by remember { mutableStateOf("") }
        if (imageUrl.isNotBlank()) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.placeholder),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
            )
        }
        TextField(
            value = imageUrl,
            onValueChange = { newValue -> imageUrl = newValue },
            label = {
                Text(text = stringResource(id = R.string.product_image_url))
            },
            modifier = Modifier
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Next,
            )
        )

        var name by remember { mutableStateOf("") }
        TextField(
            value = name, onValueChange = { newValue -> name = newValue },
            label = {
                Text(text = stringResource(id = R.string.product_name))
            },
            modifier = Modifier
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
            )
        )

        var price by remember { mutableStateOf("") }
        val formatter = remember { DecimalFormat("#.##") }
        TextField(
            value = price,
            onValueChange = { newValue ->
                try {
                    price = formatter.format(BigDecimal(newValue))
                } catch (e: IllegalArgumentException) {
                    if (newValue.isBlank()) {
                        price = newValue
                    }
                }
            },
            label = {
                Text(text = stringResource(id = R.string.product_price))
            },
            modifier = Modifier
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next,
            )
        )

        var description by remember { mutableStateOf("") }
        TextField(
            value = description, onValueChange = { newValue -> description = newValue },
            label = {
                Text(text = stringResource(id = R.string.product_description))
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                keyboardType = KeyboardType.Text,
            )
        )

        Button(
            onClick = {
                val convertedPrice = try {
                    BigDecimal(price)
                } catch (e: NumberFormatException) {
                    BigDecimal.ZERO
                }

                val product = Product(
                    name = name,
                    image = imageUrl,
                    price = convertedPrice,
                    description = description,
                )

                onSaveClick(product)
            },
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(stringResource(id = R.string.save))
        }

        Spacer(Modifier)
    }
}

@Preview(showSystemUi = true)
@Composable
fun ProductFormScreenPreview() {
    AluveryTheme {
        Surface {
            ProductFormScreen()
        }
    }
}