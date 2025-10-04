package com.example.gamerapp.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.gamerapp.R
import com.example.gamerapp.ui.theme.GamerAppTheme

data class BookmarkedGame(
    val id: Int,
    val title: String,
    val platform: String,
    val rating: String,
    val imageRes: Int
)

@Composable
fun ProfileScreen() {
    val darkTheme = isSystemInDarkTheme()
    val backgroundColor = if(darkTheme) Color(0xFF121212) else Color.White
    val textColor = if(darkTheme) Color.White else Color.Black
    val secondaryTextColor = if(darkTheme) Color.LightGray else Color.Gray
    val cardColor = if(darkTheme) Color(0xFF1E1E1E) else Color.White
    val buttonColor = Color(0xFFE91E63)

    val bookmarkedGames = listOf(
        BookmarkedGame(
            id = 1,
            title = "Red Dead Redemption 2",
            platform = "PS4 Games",
            rating = "70.0 $",
            imageRes = R.drawable.red_dead_redemption
        ),
        BookmarkedGame(
            id = 2,
            title = "Grand Theft Auto 5",
            platform = "PS4 Games",
            rating = "65.0 $",
            imageRes = R.drawable.gta5
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(32.dp))
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(buttonColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile Picture",
                    tint = Color.White,
                    modifier = Modifier.size(60.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("FullName", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = textColor)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Email", fontSize = 14.sp, color = secondaryTextColor)
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text("Edit Profile", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text("Bookmarks", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = textColor, modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp))
            Spacer(modifier = Modifier.height(16.dp))
        }

        items(bookmarkedGames) { game ->
            BookmarkedGameCard(game, buttonColor, cardColor, textColor, secondaryTextColor)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun BookmarkedGameCard(
    game: BookmarkedGame,
    primaryColor: Color,
    cardColor: Color,
    textColor: Color,
    secondaryTextColor: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = game.imageRes),
                contentDescription = game.title,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(game.title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = textColor)
                Spacer(modifier = Modifier.height(4.dp))
                Text(game.platform, fontSize = 14.sp, color = secondaryTextColor)
                Spacer(modifier = Modifier.height(4.dp))
                Text(game.rating, fontSize = 14.sp, color = textColor)
            }
            IconButton(onClick = { }) {
                Icon(Icons.Default.Bookmark, contentDescription = "Bookmarked", tint = primaryColor, modifier = Modifier.size(24.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    GamerAppTheme {
        ProfileScreen()
    }
}
