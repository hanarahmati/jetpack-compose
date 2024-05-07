package co.fanavari.mycomposeapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import co.fanavari.mycomposeapplication.ui.theme.MyComposeApplicationTheme
import kotlinx.coroutines.launch

class MainActivity4 : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeApplicationTheme() {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        AppBar(
                            onNavigationIconClick = {
                                scope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            }
                        )
                    },
                    drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
                    drawerContent = {
                        DrawerHeader()
                        DrawerBody(
                            items = listOf(
                                MenuItem(
                                    id = "home",
                                    title = "Home",
                                    contentDescription = "Go to home screen",
                                    icon = Icons.Default.Home
                                ),
                                MenuItem(
                                    id = "settings",
                                    title = "Settings",
                                    contentDescription = "Go to settings screen",
                                    icon = Icons.Default.Settings
                                ),
                                MenuItem(
                                    id = "help",
                                    title = "Help",
                                    contentDescription = "Get help",
                                    icon = Icons.Default.Info
                                ),
                            ),
                            onItemClick = {
                                //println("Clicked on ${it.title}")
                                    menuItem ->
                                when (menuItem.id) {
                                    "home" -> navController.navigate("login")
                                    "settings" -> navController.navigate("profile/hana/userid/123456789") // Add your settings route
                                    "help" -> navController.navigate("post/true") // Add your help route
                                    else -> println("Unknown menu item: ${menuItem.id}")
                                }
                            }
                        )
                    }
                ) {

                    NavHost(
                        navController = navController,
                        startDestination = "login"
                    ) {
                        composable("login") {
                            LoginScreen(navController)
                        }
                        composable(
                            route = "profile/{name}/{userId}/{timestamp}",
                            arguments = listOf(
                                navArgument("name") {
                                    type = NavType.StringType
                                },
                                navArgument("userId") {
                                    type = NavType.StringType
                                },
                                navArgument("timestamp") {
                                    type = NavType.LongType
                                },
                            )
                        ) {
                            val name = it.arguments?.getString("name")!!
                            val userId = it.arguments?.getString("userId")!!
                            val timestamp = it.arguments?.getLong("timestamp")!!
                            val viewModel: ProfileScreenViewModel = viewModel()

                            ProfileScreen(
                                navController = navController,
                                name = name,
                                userId = userId,
                                created = timestamp,
                                viewModel
                            )
                        }
                        composable("post/{showOnlyPostsByUser}", arguments = listOf(
                            navArgument("showOnlyPostsByUser") {
                                type = NavType.BoolType
                                defaultValue = false
                            }
                        )) {
                            val showOnlyPostsByUser =
                                it.arguments?.getBoolean("showOnlyPostsByUser") ?: false
                            PostScreen(showOnlyPostsByUser)
                        }
                    }
                }
            }
        }
    }
}