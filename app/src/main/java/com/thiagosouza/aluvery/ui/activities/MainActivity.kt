package com.thiagosouza.aluvery.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.thiagosouza.aluvery.R
import com.thiagosouza.aluvery.dao.ProductDao
import com.thiagosouza.aluvery.sampledata.sampleSections
import com.thiagosouza.aluvery.ui.screens.HomeScreen
import com.thiagosouza.aluvery.ui.screens.HomeScreenUiState
import com.thiagosouza.aluvery.ui.theme.AluveryTheme

class MainActivity : ComponentActivity() {
    private val productDao = ProductDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(
                onFloatActionButtonClick = {
                    val intent = Intent(this, ProductFormActivity::class.java)
                    startActivity(intent)
                }
            ) {
                val products = productDao.products()
                HomeScreen(products = products)
            }
        }
    }
}

@Composable
fun App(
    onFloatActionButtonClick: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    AluveryTheme {
        Surface {
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(onClick = onFloatActionButtonClick) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(id = R.string.create_product),
                        )
                    }
                }
            ) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    content()
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun AppPreview() {
    App {
        HomeScreen(
            HomeScreenUiState(sections = sampleSections)
        )
    }
}