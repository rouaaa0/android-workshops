package com.example.gamerapp.ui.theme.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gamerapp.ui.theme.GamerAppTheme
import com.example.gamerapp.ui.theme.screens.NewsScreen
import com.example.gamerapp.ui.theme.screens.ProfileScreen
import com.example.gamerapp.ui.theme.screens.StoreScreen
import kotlinx.coroutines.launch

// 🔹 Bottom navigation items
sealed class BottomNavItem(val title: String, val screen: @Composable () -> Unit) {
    object News : BottomNavItem("News", { NewsScreen() })
    object Store : BottomNavItem("Store", { StoreScreen() })
    object Profile : BottomNavItem("Profile", { ProfileScreen() })
}

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GamerAppTheme {
                HomeScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val darkTheme = isSystemInDarkTheme()
    val backgroundColor = if (darkTheme) Color(0xFF121212) else Color.White
    var selectedTab by remember { mutableStateOf<BottomNavItem>(BottomNavItem.News) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        containerColor = backgroundColor,
        topBar = {
            TopAppBar(
                title = { Text(selectedTab.title) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = if(darkTheme) Color(0xFF1E1E1E) else Color(0xFFE91E63),
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = {
                        scope.launch { snackbarHostState.showSnackbar("Coming soon") }
                    }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notifications")
                    }

                    if (selectedTab is BottomNavItem.Store) {
                        IconButton(onClick = {
                            scope.launch { snackbarHostState.showSnackbar("Cart coming soon") }
                        }) {
                            Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
                        }
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = if(darkTheme) Color(0xFF1E1E1E) else Color(0xFFE91E63),
                contentColor = Color.White
            ) {
                listOf(BottomNavItem.News, BottomNavItem.Store, BottomNavItem.Profile).forEach { item ->
                    val icon = when(item) {
                        BottomNavItem.News -> Icons.Default.Article
                        BottomNavItem.Store -> Icons.Default.ShoppingCart
                        BottomNavItem.Profile -> Icons.Default.Person
                        else -> Icons.Default.Home
                    }

                    NavigationBarItem(
                        icon = { Icon(icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = item == selectedTab,
                        onClick = { selectedTab = item },
                        alwaysShowLabel = true
                    )
                }
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(backgroundColor)
        ) {
            selectedTab.screen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    GamerAppTheme {
        HomeScreen()
    }
}
