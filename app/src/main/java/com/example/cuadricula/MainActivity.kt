package com.example.cuadricula

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.cuadricula.data.DataSource
import com.example.cuadricula.model.Topic
import com.example.cuadricula.ui.theme.CuadriculaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CuadriculaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TopicApp()
                }
            }
        }
    }
}

@Composable
fun TopicApp() {
    TopicList(
        topicList = DataSource.topics,
        modifier = Modifier.padding(dimensionResource(R.dimen.padding_pequeño))
    )
}

@Composable
fun TopicList(topicList: List<Topic>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_pequeño)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_pequeño)),
        modifier = modifier) {
        items(topicList) { topic ->
            TopicCard(
                topic = topic

            )
        }
    }
}


@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Row {
            Column {
                Image(
                    painter = painterResource(topic.imageResourceId),
                    contentDescription = null,
                    modifier = Modifier
                        .height(dimensionResource(R.dimen.padding_imagen))
                        .width(dimensionResource(R.dimen.padding_imagen)),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.padding_mediano),
                    end = dimensionResource(R.dimen.padding_mediano)
                )
            ) {
                Text(
                    text = stringResource(topic.stringResourceId),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_mediano),
                        bottom = dimensionResource(R.dimen.padding_pequeño)
                    )
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = null,
                        modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_mediano))
                    )
                    Text(
                        text = topic.cantidadCurso.toString(),
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_mediano))
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun TopicCardPreview() {
    TopicApp()
}