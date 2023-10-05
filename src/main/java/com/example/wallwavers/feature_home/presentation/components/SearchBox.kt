package com.example.wallwavers.feature_home.presentation.components

import android.util.Log
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.wallwavers.R

@Composable
fun SearchBox() {
    TextField(
        value = stringResource(id = R.string.search_box),
        onValueChange = {
            Log.d("test", it)
        }
    )
}