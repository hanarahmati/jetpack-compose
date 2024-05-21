package co.fanavari.mycomposeapplication.screens

import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import co.fanavari.mycomposeapplication.screens.components.FilterChipExample
import co.fanavari.mycomposeapplication.viewmodel.ProfileScreenViewModel
import co.fanavari.mycomposeapplication.model.User
import co.fanavari.mycomposeapplication.screens.components.ConfirmationDialog
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

        FilterChipExample(selected = profileScreenViewModel.selected.value) {
            profileScreenViewModel.onSelectedChange() // Update state in ViewModel
        }
        // Display the filtered content
        profileScreenViewModel.filteredItems.value.forEach { item ->
            Text(text = item)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {profileScreenViewModel.onDialogShow()}) {
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

