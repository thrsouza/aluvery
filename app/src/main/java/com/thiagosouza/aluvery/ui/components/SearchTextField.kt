package com.thiagosouza.aluvery.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.thiagosouza.aluvery.R

@Composable
fun SearchTextField(
    label: String,
    placeholder: String,
    value: String,
    onValueChanged: (String) -> Unit,
) {
    Surface(elevation = 4.dp) {
        OutlinedTextField(
            value = value,
            onValueChange = { newValue ->
                onValueChanged(newValue)
            },
            shape = RoundedCornerShape(50),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            },
            label = {
                Text(label)
            },
            placeholder = {
                Text(text = placeholder)
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        )
    }
}