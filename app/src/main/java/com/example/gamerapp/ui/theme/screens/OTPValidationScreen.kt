package com.example.gamerapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OTPValidationScreen(navController: NavController, code: String) {
    val snackbarHostState = remember { SnackbarHostState() }

    var d1 by remember { mutableStateOf("") }
    var d2 by remember { mutableStateOf("") }
    var d3 by remember { mutableStateOf("") }
    var d4 by remember { mutableStateOf("") }
    var showWrongCodeSnackbar by remember { mutableStateOf(false) }
    var showComingSoonSnackbar by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(showWrongCodeSnackbar) {
        if (showWrongCodeSnackbar) {
            snackbarHostState.showSnackbar("Wrong code!")
            showWrongCodeSnackbar = false
        }
    }

    LaunchedEffect(showComingSoonSnackbar) {
        if (showComingSoonSnackbar) {
            snackbarHostState.showSnackbar("Coming soon.")
            showComingSoonSnackbar = false
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("OTP Validation") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Enter the code sent to you by\nEmail or Mobile number",
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                listOf(
                    Pair(d1) { v: String -> if (v.length <= 1 && v.all { it.isDigit() }) { d1 = v; if (v.length == 1) focusManager.moveFocus(androidx.compose.ui.focus.FocusDirection.Next) } },
                    Pair(d2) { v: String -> if (v.length <= 1 && v.all { it.isDigit() }) { d2 = v; if (v.length == 1) focusManager.moveFocus(androidx.compose.ui.focus.FocusDirection.Next) } },
                    Pair(d3) { v: String -> if (v.length <= 1 && v.all { it.isDigit() }) { d3 = v; if (v.length == 1) focusManager.moveFocus(androidx.compose.ui.focus.FocusDirection.Next) } },
                    Pair(d4) { v: String -> if (v.length <= 1 && v.all { it.isDigit() }) { d4 = v; if (v.length == 1) focusManager.clearFocus() } }
                ).forEach { (value, onChange) ->
                    OutlinedTextField(
                        value = value,
                        onValueChange = onChange,
                        modifier = Modifier.width(56.dp),
                        singleLine = true,
                        textStyle = LocalTextStyle.current.copy(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                        visualTransformation = VisualTransformation.None,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val entered = d1 + d2 + d3 + d4
                    if (entered == code) {
                        navController.navigate("reset")
                    } else {
                        showWrongCodeSnackbar = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Verify", color = MaterialTheme.colorScheme.onPrimary)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                "Didn't receive a verification code?",
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
            )
            TextButton(onClick = { showComingSoonSnackbar = true }) {
                Text("Resend code", color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OTPValidationScreenPreview() {
    val navController = rememberNavController()
    OTPValidationScreen(navController = navController, code = "1234")
}
