package com.thiagosouza.aluvery.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import com.thiagosouza.aluvery.dao.ProductDao
import com.thiagosouza.aluvery.ui.screens.ProductFormScreen
import com.thiagosouza.aluvery.ui.screens.ProductFormScreenUiState
import com.thiagosouza.aluvery.ui.theme.AluveryTheme

class ProductFormActivity : ComponentActivity() {
    private val productDao = ProductDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                Surface {
                    val state = ProductFormScreenUiState(
                        onSaveProduct = { product ->
                            productDao.save(product)
                            finish()
                        }
                    )
                    
                    ProductFormScreen(state = state)
                }
            }
        }
    }
}
