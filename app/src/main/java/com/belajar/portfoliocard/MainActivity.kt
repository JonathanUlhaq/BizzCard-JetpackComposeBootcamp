package com.belajar.portfoliocard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.belajar.portfoliocard.ui.theme.PortfolioCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PortfolioCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CreatePortCard()
                }
            }
        }
    }
}

@Composable
fun CreatePortCard() {
    var showContent by remember {
        mutableStateOf(false)
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(390.dp)
                .padding(12.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = 4.dp
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProfileImage()
                Spacer(modifier = Modifier.height(4.dp))
                Divider(
                    thickness = 2.dp,
                )
                PortText()
                Button(onClick = { showContent = !showContent}) {
                    Text(text = "Portfolio",
                        style = MaterialTheme.typography.button)
                }
                Spacer(modifier = Modifier.height(4.dp))
                AnimatedVisibility(visible = showContent,
                    enter = expandVertically(
                        tween(1000),
                        expandFrom = Alignment.Top
                    ),
                    exit =
                        shrinkVertically(
                            tween(1000),
                            shrinkTowards = Alignment.Top
                        )

                ) {
                    ContentPortfolio()
                }
            }
        }
    }
}

@Composable
private fun ContentPortfolio() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Surface(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            shape = RoundedCornerShape(6.dp),
            border = BorderStroke(2.dp, Color.LightGray)
        ) {
            ItemContent(data = listOf(
                "Project 1",
                "Project 2",
                "Project 3",
                "Project 4",
                "Project 5",
            ))
        }
    }
}

@Composable
private fun ItemContent(data:List<String>) {
    LazyColumn(content = {
        items(data) {
            item ->
            PerItemContent(item)
        }
    })
}

@Composable
private fun PerItemContent(title:String) {
    Card(
        modifier = Modifier
            .padding(13.dp)
            .fillMaxWidth(),
        shape = RectangleShape,
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .background(MaterialTheme.colors.surface)
                .padding(7.dp)
        ) {
            ProfileImage(
                modifier = Modifier
                    .size(100.dp)
            )

            Column(
                modifier = Modifier
                    .padding(7.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    title,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "A Great Project",
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Composable
private fun PortText() {
    Column(
        modifier = Modifier.padding(5.dp)
    ) {
        Text(
            text = "Jovian N. U",
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primaryVariant
        )

        Text(
            text = "Native Android Developer",
            modifier = Modifier
                .padding(3.dp)
        )

        Text(
            text = "@jo.naathaan",
            modifier = Modifier
                .padding(3.dp)
        )
    }
}

@Composable
private fun ProfileImage(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        elevation = 4.dp,
        border = BorderStroke(0.2.dp, Color.LightGray)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = null,
            modifier = modifier
                .size(135.dp),
            contentScale = ContentScale.Crop
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PortfolioCardTheme {
        CreatePortCard()
    }
}