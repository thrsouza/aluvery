package com.thiagosouza.aluvery.ui.screens

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
import com.thiagosouza.aluvery.models.Product
import com.thiagosouza.aluvery.ui.theme.AluveryTheme
import java.math.BigDecimal
import java.text.DecimalFormat

class ProductFormScreenUiState(
    val onSaveProduct: (Product) -> Unit = {}
) {
    var imageUrl by mutableStateOf("")
        private set

    var name by mutableStateOf("")
        private set

    var price by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set


    val onImageUrlValueChanged: (String) -> Unit = { newValue ->
        imageUrl = newValue
    }

    val onNameValueChanged: (String) -> Unit = { newValue ->
        name = newValue
    }

    val onPriceValueChanged: (String) -> Unit = { newValue ->
        try {
            price = formatter.format(BigDecimal(newValue))
        } catch (e: IllegalArgumentException) {
            if (newValue.isBlank()) {
                price = newValue
            }
        }
    }

    val onDescriptionValueChanged: (String) -> Unit = { newValue ->
        description = newValue
    }

    val onSaveButtonClick: () -> Unit = {
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

        onSaveProduct(product)
    }

    private val formatter = DecimalFormat("#.##")

    fun isToShowTheImagePreview(): Boolean {
        return imageUrl.isNotBlank()
    }
}

@Composable
fun ProductFormScreen(
    state: ProductFormScreenUiState = ProductFormScreenUiState(),
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

        if (state.isToShowTheImagePreview()) {
            AsyncImage(
                model = state.imageUrl,
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
            value = state.imageUrl,
            onValueChange = state.onImageUrlValueChanged,
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

        TextField(
            value = state.name,
            onValueChange = state.onNameValueChanged,
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

        TextField(
            value = state.price,
            onValueChange = state.onPriceValueChanged,
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

        TextField(
            value = state.description,
            onValueChange = state.onDescriptionValueChanged,
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
            onClick = state.onSaveButtonClick,
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