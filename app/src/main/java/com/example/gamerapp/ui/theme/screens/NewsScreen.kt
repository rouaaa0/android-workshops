package com.example.gamerapp.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.gamerapp.R
import com.example.gamerapp.ui.theme.GamerAppTheme

@Composable
fun NewsScreen() {
    val darkTheme = isSystemInDarkTheme()
    val backgroundColor = if(darkTheme) Color(0xFF121212) else Color.White
    val textColor = if(darkTheme) Color.White else Color.Black
    val secondaryTextColor = if(darkTheme) Color.LightGray else Color.Gray

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Image(
                painter = painterResource(id = R.drawable.top_games),
                contentDescription = "Top 10 Games",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Top 10 Gaming News of the day",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Here's the latest news from the gaming and e-sports world.",
                fontSize = 14.sp,
                color = secondaryTextColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Show More",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFFE91E63)
            )
        }

        item {
            GameNewsCard(
                title = "The Legend of Zelda",
                imageRes = R.drawable.zelda
            )
        }
    }
}

@Composable
fun GameNewsCard(title: String, imageRes: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = title,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f))
                    )
                )
        )
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun NewsScreenPreview() {
    GamerAppTheme {
        NewsScreen()
    }
}
