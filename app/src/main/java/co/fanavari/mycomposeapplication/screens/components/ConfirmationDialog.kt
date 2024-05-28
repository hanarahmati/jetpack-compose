package co.fanavari.mycomposeapplication.screens.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ConfirmationDialog(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Confirm Action")
        },
        text = {
            Text("Are you sure you want to log out?")
        },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Confirm")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Dismiss")
            }
        }
    )
}

@Composable
@Preview
fun ConfirmationDialogPreview() {
    ConfirmationDialog(onConfirm = {}, onDismiss = {}) // Provide empty callbacks for preview
}