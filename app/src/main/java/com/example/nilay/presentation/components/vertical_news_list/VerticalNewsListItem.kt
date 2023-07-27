package com.example.nilay.presentation.components.vertical_news_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nilay.data.model.news.Source
import com.example.nilay.domain.model.Article
import com.example.nilay.presentation.components.NetworkImage
import com.example.nilay.presentation.ui.theme.AppTheme

@Composable
fun VerticalNewsListItem(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
    article: Article,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clip(
                shape = RoundedCornerShape(8.dp)
            )
            .background(
                MaterialTheme.colorScheme.secondaryContainer
                    .copy(alpha = 0.1f)
            )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row {
                NetworkImage(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .height(200.dp)
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(8.dp)),
                    url = article.urlToImage ?: "",
                    contentDescription = article.urlToImage ?: "",
                )
            }

            Row {
                Text(
                    modifier = Modifier.padding(
                        horizontal = 12.dp,
                        vertical = 4.dp
                    ),
                    text = article.title!!,
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontWeight = FontWeight.ExtraBold,
                    ),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Row(
                modifier = Modifier.absolutePadding(bottom = 12.dp)
            ) {

                Text(
                    modifier = modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    text = article.description!!,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                )
            }

        }

    }
}


@Composable
fun VerticalarticleListItemShimmer() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(
                MaterialTheme.colorScheme.secondaryContainer
                    .copy(alpha = 0.1f)
            )
    ) {
        Row {
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .background(
                        MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.3f
                        )
                    )
            )

            Column(
                modifier = Modifier.height(100.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .height(20.dp)
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(4.dp))
                        .background(
                            MaterialTheme.colorScheme.primary.copy(
                                alpha = 0.1f
                            )
                        )
                )
                Box(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .height(16.dp)
                        .fillMaxWidth(0.3f)
                        .clip(shape = RoundedCornerShape(4.dp))
                        .background(
                            MaterialTheme.colorScheme.primary.copy(
                                alpha = 0.1f
                            )
                        )
                )

                Box(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .height(12.dp)
                        .fillMaxWidth(0.7f)
                        .clip(shape = RoundedCornerShape(4.dp))
                        .background(
                            MaterialTheme.colorScheme.primary.copy(
                                alpha = 0.1f
                            )
                        )
                )
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewVerticalarticleListItemShimmer() {
    AppTheme {
        VerticalNewsListItem(
            article = Article(
                "test",
                "content",
                "Description",
                "",
                Source("", ""),
                "tset setr sete tes ts",
                "",
                ""
            )
        )
    }
}