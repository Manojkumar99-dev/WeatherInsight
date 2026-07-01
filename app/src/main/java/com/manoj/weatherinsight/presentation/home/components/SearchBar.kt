package com.manoj.weatherinsight.presentation.home.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.manoj.weatherinsight.R

/**
 * A search bar component that allows users to type a city name and trigger a search.
 *
 * @param city The current text value in the search field.
 * @param isLoading Whether a search operation is currently in progress.
 * @param onCityChanged Callback triggered when the search text changes.
 * @param onSearch Callback triggered when the search is submitted (via button or keyboard).
 * @author Manojkumar
 */
@Composable
fun SearchBar(
    city: String,
    isLoading: Boolean,
    onCityChanged: (String) -> Unit,
    onSearch: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = city,
            onValueChange = onCityChanged,
            modifier = Modifier
                .weight(1f)
                .padding(end = 12.dp),
            singleLine = true,
            shape = RoundedCornerShape(16.dp),

            textStyle = TextStyle(
                color = Color.White
            ),

            placeholder = {
                Text(
                    text = stringResource(R.string.search_city),
                    color = Color.LightGray
                )
            },

            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.White
                )
            },

            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,

                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.Gray,

                cursorColor = Color.White,

                focusedPlaceholderColor = Color.LightGray,
                unfocusedPlaceholderColor = Color.Gray,

                focusedLeadingIconColor = Color.White,
                unfocusedLeadingIconColor = Color.LightGray,

                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),

            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),

            keyboardActions = KeyboardActions(
                onSearch = {
                    focusManager.clearFocus()
                    onSearch()
                }
            )
        )

        Button(
            enabled = !isLoading,
            onClick = {
                focusManager.clearFocus()
                onSearch()
            },
            shape = RoundedCornerShape(16.dp)
        ) {
            if (isLoading) {
                // Show a loading spinner inside the button if searching.
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp
                )
            } else {
                Text(stringResource(R.string.go))
            }
        }
    }
}
