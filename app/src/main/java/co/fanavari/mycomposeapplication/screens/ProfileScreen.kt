package co.fanavari.mycomposeapplication.screens

import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import co.fanavari.mycomposeapplication.model.User
import co.fanavari.mycomposeapplication.screens.components.ConfirmationDialog
import co.fanavari.mycomposeapplication.screens.components.FilterChipExample
import co.fanavari.mycomposeapplication.viewmodel.ProfileScreenViewModel
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId


@Composable
fun ProfileScreen(
    navController: NavController,
    name: String,
    userId: String,
    created: Long,
    profileScreenViewModel: ProfileScreenViewModel = viewModel()
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

    var showDialog by profileScreenViewModel.showDialog

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(128.dp))
            Text("Profile Screen: $user", textAlign = TextAlign.Center)
            Button(onClick = {
                navController.navigate("post/true")
            }) {
                Text("Go to Post Screen")
            }

            FilterChipExample(selected = profileScreenViewModel.selected.value) {
                profileScreenViewModel.onSelectedChange() // Update state in ViewModel
            }
            // Display the filtered content
            profileScreenViewModel.filteredItems.value.forEach { item ->
                Text(text = item)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("tasks") }) {
                Text("Go to Task Manager")
            }

        }
        Button(
            modifier = Modifier
                .padding(56.dp)
                .align(Alignment.CenterHorizontally),
            onClick = { profileScreenViewModel.onDialogShow() }) {
            Text(text = "Show Dialog For Log Out")

        }
    }

    if (showDialog) {
        ConfirmationDialog(
            onConfirm = {
                profileScreenViewModel.onDialogConfirm()
                navController.navigate("login")
            },
            onDismiss = { profileScreenViewModel.onDialogDismiss() }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        navController = rememberNavController(),
        name = "John Doe",
        userId = "user123",
        created = 1656816000000L // Example timestamp in milliseconds
    )
}