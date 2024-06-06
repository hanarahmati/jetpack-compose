package co.fanavari.mycomposeapplication.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import co.fanavari.mycomposeapplication.data.model.MenuItem
import co.fanavari.mycomposeapplication.navigation.NavGraph
import co.fanavari.mycomposeapplication.screens.components.AppBar
import co.fanavari.mycomposeapplication.screens.components.DrawerBody
import co.fanavari.mycomposeapplication.screens.components.DrawerHeader
import co.fanavari.mycomposeapplication.ui.theme.MyComposeApplicationTheme
import kotlinx.coroutines.launch

class MainActivity4 : ComponentActivity() {
    //    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeApplicationTheme() {
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet {
                            DrawerHeader()
                            DrawerBody(
                                items = listOf(
                                    MenuItem(
                                        id = "login",
                                        title = "Login",
                                        contentDescription = "Go to login screen",
                                        icon = Icons.Default.Add
                                    ),
                                    MenuItem(
                                        id = "profile",
                                        title = "Profile",
                                        contentDescription = "Go to profile screen",
                                        icon = Icons.Default.Person
                                    ),
                                    MenuItem(
                                        id = "post",
                                        title = "Post",
                                        contentDescription = "Post",
                                        icon = Icons.Default.Email
                                    ),
                                ),
                                onItemClick = {
                                    //println("Clicked on ${it.title}")
                                        menuItem ->
                                    scope.launch {
                                        drawerState.close()
                                        when (menuItem.id) {
                                            "login" -> navController.navigate("login")
                                            "profile" -> navController.navigate("profile/hana/userid/123456789") // Add your settings route
                                            "post" -> navController.navigate("post/true") // Add your help route
                                            else -> println("Unknown menu item: ${menuItem.id}")
                                        }
                                    }
                                }
                            )
                        }

                    },
                    content = {

                        Scaffold(
                            topBar = {
                                AppBar(
                                    onNavigationIconClick = {
                                        scope.launch {
                                            drawerState.open()
                                        }
                                    }
                                )
                            },
                            snackbarHost = { SnackbarHost(snackbarHostState) },
                            content = { innerPadding ->

                                NavGraph(
                                    navController = navController,
                                    modifier = Modifier.padding(innerPadding)
                                )

                            }
                        )
                    }

                )

            }
        }
    }
}