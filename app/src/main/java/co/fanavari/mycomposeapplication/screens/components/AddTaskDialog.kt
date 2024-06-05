package co.fanavari.mycomposeapplication.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.fanavari.mycomposeapplication.model.Task
import java.util.UUID

@Composable
fun AddTaskDialog(onAdd: (Task) -> Unit, onDismiss: () -> Unit) {
    var taskName by remember { mutableStateOf("") }
    var taskPriority by remember { mutableStateOf(1f) }
    var isImportant by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Add Task") },
        text = {
            Column {
                OutlinedTextField(
                    value = taskName,
                    onValueChange = { taskName = it },
                    label = { Text("Task Name") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("Priority: ${taskPriority.toInt()}")
                Slider(
                    value = taskPriority,
                    onValueChange = { taskPriority = it },
                    valueRange = 1f..5f,
                    steps = 3
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Important")
                    Switch(checked = isImportant, onCheckedChange = { isImportant = it })
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                val id = UUID.randomUUID().toString()
                onAdd(Task(id,taskName, taskPriority.toInt(), isImportant, false))
            }) {
                Text("Add")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AddTaskDialogPreview() {
    AddTaskDialog(onAdd = {}, onDismiss = {})
}