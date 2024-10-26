package com.itami.typesafe_navigation_compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.itami.typesafe_navigation_compose.navigation.GreetingScreen
import com.itami.typesafe_navigation_compose.navigation.StartScreen

@Composable
fun NavHostApp(navHostController: NavHostController){
    NavHost(
        navController = navHostController,
        startDestination = StartScreen
    ) {
        composable<StartScreen> {
            var name by remember { mutableStateOf("") }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Start Screen",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Spacer(modifier = Modifier.height(12.dp))
                TextField(
                    value = name,
                    onValueChange = { newValue ->
                        name = newValue
                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = { navHostController.navigate(GreetingScreen(name = name)) }
                ) {
                    Text(
                        text = "Greet me",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
        }
        composable<GreetingScreen> {
            val args = it.toRoute<GreetingScreen>()
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Greeting Screen.",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Hello, ${args.name}! Hope you're doing well!",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        }
    }
}

data class Contact(val name:String,val id:String, val phone:String)