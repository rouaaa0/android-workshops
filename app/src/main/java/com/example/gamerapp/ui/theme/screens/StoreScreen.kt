package com.example.gamerapp.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamerapp.R
import com.example.gamerapp.ui.theme.GamerAppTheme

data class Game(
    val id: Int,
    val title: String,
    val platform: String,
    val price: String,
    val imageRes: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreScreen() {
    val darkTheme = isSystemInDarkTheme()
    val backgroundColor = if (darkTheme) Color(0xFF121212) else Color(0xFFF5F5F5)
    val cardColor = if (darkTheme) Color(0xFF1E1E1E) else Color.White
    val textColor = if (darkTheme) Color.White else Color.Black
    val secondaryTextColor = if (darkTheme) Color.LightGray else Color.Gray
    val accentColor = Color(0xFFE91E63)

    val games = listOf(
        Game(1, "Red Dead Redemption 2", "PS4 Games", "10.0 $", R.drawable.red_dead_redemption),
        Game(2, "Grand Theft Auto 5", "PS4 Games", "20.0 $", R.drawable.gta5),
        Game(3, "The Legend of Zelda", "Nintendo Switch", "90.0 $", R.drawable.zelda),
        Game(4, "God of War Ragnarok", "PS5 Games", "70.0 $", R.drawable.gow)
    )

    Scaffold(
        containerColor = backgroundColor,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                containerColor = accentColor,
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(games) { game ->
                GameItem(game, cardColor, textColor, secondaryTextColor, accentColor)
            }
        }
    }
}

@Composable
fun GameItem(
    game: Game,
    cardColor: Color,
    textColor: Color,
    secondaryTextColor: Color,
    accentColor: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Game Image
            Image(
                painter = painterResource(id = game.imageRes),
                contentDescription = game.title,
                modifier = Modifier
                    .width(80.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )

            // Game Info
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = game.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = accentColor
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = game.platform,
                        fontSize = 12.sp,
                        color = secondaryTextColor
                    )
                }
                Text(
                    text = game.price,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = textColor
                )
            }

            // Cart Icon
            IconButton(
                onClick = { },
                modifier = Modifier.align(Alignment.Top)
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Add to cart",
                    tint = accentColor
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StoreScreenPreview() {
    GamerAppTheme {
        StoreScreen()
    }
}
