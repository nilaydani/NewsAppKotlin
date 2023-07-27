package com.example.nilay.presentation.discover.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nilay.presentation.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoverSearchCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        ),
        onClick = onClick,
    ) {

        Box(modifier = Modifier.padding(vertical = 8.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp,),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            shape = MaterialTheme.shapes.large,
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = "Search"
                )
                Text(
                    text = "Search News...",
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Box(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.onTertiaryContainer,
                            shape = MaterialTheme.shapes.small
                        ),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(12.dp),
                        imageVector = Icons.Rounded.Send,
                        tint = MaterialTheme.colorScheme.tertiaryContainer,
                        contentDescription = "Search"
                    )
                }

            }
        }

        Box(modifier = Modifier.padding(vertical = 8.dp))


    }
}

@Preview(name = "DiscoverSearchCard")
@Composable
private fun PreviewDiscoverSearchCard() {
    AppTheme {
        DiscoverSearchCard()
    }
}