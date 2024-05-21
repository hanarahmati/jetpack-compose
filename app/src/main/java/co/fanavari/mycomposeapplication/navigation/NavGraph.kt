package co.fanavari.mycomposeapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import co.fanavari.mycomposeapplication.screens.LoginScreen
import co.fanavari.mycomposeapplication.screens.PostScreen
import co.fanavari.mycomposeapplication.screens.ProfileScreen
import co.fanavari.mycomposeapplication.screens.TaskManagerScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {

    NavHost(
        navController = navController, startDestination = "login", modifier = modifier
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

            ProfileScreen(
                navController = navController, name = name, userId = userId, created = timestamp
            )
        }
        composable(
            "post/{showOnlyPostsByUser}",
            arguments = listOf(navArgument("showOnlyPostsByUser") {
                type = NavType.BoolType
                defaultValue = false
            })
        ) {
            val showOnlyPostsByUser = it.arguments?.getBoolean("showOnlyPostsByUser") ?: false
            PostScreen(showOnlyPostsByUser)
        }
        composable(
            "tasks"
        ){
            TaskManagerScreen(navController)
        }
    }
}