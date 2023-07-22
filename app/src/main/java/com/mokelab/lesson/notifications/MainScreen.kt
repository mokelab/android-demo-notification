package com.mokelab.lesson.notifications

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    toStandard: () -> Unit,
    toAction: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.app_name)) })
        }
    ) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            item {
                ListItem(
                    headlineContent = {
                        Text("Standard")
                    },
                    modifier = Modifier.clickable {
                        toStandard()
                    }
                )
                ListItem(
                    headlineContent = {
                        Text("Action")
                    },
                    modifier = Modifier.clickable {
                        toAction()
                    }
                )
            }
        }
    }
}