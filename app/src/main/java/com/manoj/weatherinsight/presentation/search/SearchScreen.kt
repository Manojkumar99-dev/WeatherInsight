package com.manoj.weatherinsight.presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.manoj.weatherinsight.R

/**
 * A secondary screen intended for advanced searching or location management.
 * Currently a placeholder showing the screen name.
 *
 * @author Manojkumar
 */
@Composable
fun SearchScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(stringResource(R.string.search_screen))
    }
}
