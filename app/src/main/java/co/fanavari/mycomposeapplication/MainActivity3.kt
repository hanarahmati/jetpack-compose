package co.fanavari.mycomposeapplication

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import co.fanavari.mycomposeapplication.ui.theme.MyComposeApplicationTheme
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class MainActivity3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeApplicationTheme() {
                val navController = rememberNavController()
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
                        val viewModel: ProfileScreenViewModel = viewModel()

                        val name = it.arguments?.getString("name")!!
                        val userId = it.arguments?.getString("userId")!!
                        val timestamp = it.arguments?.getLong("timestamp")!!

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

@Composable
fun LoginScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login Screen")
        Button(onClick = {
            navController.navigate("profile/hana/userid/123456789")
        }) {
            Text("Go to Profile Screen")
        }
    }
}

@Composable
fun ProfileScreen(
    navController: NavController,
    name: String,
    userId: String,
    created: Long,
    profileScreenViewModel: ProfileScreenViewModel
) {
    val user = remember {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            User(
                name = name,
                id = userId,
                created = LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(created), ZoneId.systemDefault()
                )
            )
        } else {
            TODO("VERSION.SDK_INT < O")
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Profile Screen: $user", textAlign = TextAlign.Center)
        Button(onClick = {
            navController.navigate("post/true")
        }) {
            Text("Go to Post Screen")
        }
    }
    val selected = remember { mutableStateOf(false) } // Filter criteria state
    val items = listOf("Item 1", "Item 2", "Item 3") // Replace with your data source

    fun filterItems(items: List<String>, selected: MutableState<Boolean>): List<String> {
        // Implement your filtering logic based on "selected" state
        if (selected.value) {
            return items.filter { item -> item.contains("2") } // Filter by a specific criteria
        }
        return items // No filtering if not selected
    }

    val filteredItems = filterItems(items, profileScreenViewModel.selected)

    Column {
        /*FilterChipExample(selected = selected.value) { // Pass selected state and callback
            selected.value = !selected.value // Update selected on chip click
        } // Add the filter chip*/

        FilterChipExample(selected = profileScreenViewModel.selected.value) {
            profileScreenViewModel.onSelectedChange() // Update state in ViewModel
        }
        // Display the filtered content
        filteredItems.forEach { item ->
            Text(text = item)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    // Provide default values for the arguments
    ProfileScreen(
        navController = rememberNavController(), // Mock a NavController
        name = "John Doe",
        userId = "user123",
        created = 1656816000000L, // Example timestamp in milliseconds,
        ProfileScreenViewModel(initialSelected = true)
    )
}
@Composable
fun PostScreen(
    showOnlyPostsByUser: Boolean = false
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Post Screen, $showOnlyPostsByUser")
    }

}

@Composable
fun FilterChipExample(
    selected: Boolean, // Receive selected state
    onSelectedChange: () -> Unit, // Callback for selection change
) {
    var internalSelected by remember { mutableStateOf(false) }

    FilterChip(
        onClick = onSelectedChange,
        label = {
            Text("Filter chip")
        },
        selected = selected,
        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else {
            null
        },
    )
}

