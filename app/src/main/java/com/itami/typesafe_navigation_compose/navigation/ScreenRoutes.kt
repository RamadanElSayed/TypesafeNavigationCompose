package com.itami.typesafe_navigation_compose.navigation

import kotlinx.serialization.Serializable

@Serializable
object StartScreen

@Serializable
data class GreetingScreen(val name: String)