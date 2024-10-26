package com.itami.typesafe_navigation_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.erapp.newnavigationexample.navigation.HomeRoute
import com.erapp.newnavigationexample.navigation.ProfileRoute
import com.erapp.newnavigationexample.ui.screens.profile.ProfileScreen
import com.erapp.newnavigationexample.ui.screens.profile.ProfileScreenDetails
import com.itami.typesafe_navigation_compose.utils.safeNavigate
import com.itami.typesafe_navigation_compose.ui.screens.DetailsScreen
import com.itami.typesafe_navigation_compose.ui.screens.HomeScreen
import com.itami.typesafe_navigation_compose.ui.theme.TypesafeNavigationComposeTheme

class MainActivity4 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            TypesafeNavigationComposeTheme  {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    NavHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        navController = navController,
                        startDestination = HomeRoute.HomeMainRoute
                    ) {
                        homeNavGraph(navController)
                      //  profileNavGraph(navController)
                    }
                }
            }
        }
    }
}

fun NavGraphBuilder.homeNavGraph(navController: NavHostController) {
    navigation<HomeRoute.HomeMainRoute>(
        startDestination = HomeRoute.Home
    ) {
        composable<HomeRoute.Home> {
            // Home screen
            HomeScreen {
                navController.navigate(HomeRoute.Details)
            }
        }
        composable<HomeRoute.Details> {
            // Details screen
            DetailsScreen {
                navController.navigate(ProfileRoute.Profile){
                    popUpTo(route =HomeRoute.Details) { inclusive = true
                    }

                }
            }
        }

        composable<ProfileRoute.Profile> {
            // Profile screen
            ProfileScreen {
                navController.safeNavigate(route = ProfileRoute.ProfileDetails("1", "John Doe"),
                    popUpTo = ProfileRoute.Profile,
                    inclusive = true
                    )
            }


        }
        composable<ProfileRoute.ProfileDetails> {
            // Details screen
            val args = it.toRoute<ProfileRoute.ProfileDetails>()

            ProfileScreenDetails(userId = args.userId, userName = args.name) {
                navController.safeNavigate(
                    route = HomeRoute.Home,
                    popUpTo = HomeRoute.HomeMainRoute,
                    inclusive = true,
                )

            }
        }
    }
}

//fun NavGraphBuilder.profileNavGraph(navController: NavHostController) {
//    navigation<ProfileRoute.ProfileMainRoute>(
//        startDestination = ProfileRoute.Profile
//    ) {
//        composable<ProfileRoute.Profile> {
//            // Profile screen
//            ProfileScreen {
//                navController.safeNavigate(route = ProfileRoute.ProfileDetails("1", "John Doe"))
//            }
//        }
//        composable<ProfileRoute.ProfileDetails> {
//            // Details screen
//            val args = it.toRoute<ProfileRoute.ProfileDetails>()
//
//            ProfileScreenDetails(userId = args.userId, userName = args.name) {
//                navController.safeNavigate(
//                    route = HomeRoute.Home,
//                    popUpTo = HomeRoute.HomeMainRoute,
//                    inclusive = true
//                )
//            }
//        }
//    }
//}