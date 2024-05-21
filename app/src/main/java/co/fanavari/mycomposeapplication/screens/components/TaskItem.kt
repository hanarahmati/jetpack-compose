package co.fanavari.mycomposeapplication.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.fanavari.mycomposeapplication.model.Task

@Composable
fun TaskItem(task: Task) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = task.name, style = MaterialTheme.typography.titleLarge)
            Text(text = "Priority: ${task.priority}", style = MaterialTheme.typography.bodyMedium)
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Important")
                Switch(checked = task.isImportant, onCheckedChange = null)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Completed")
                Checkbox(checked = task.isCompleted, onCheckedChange = null)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskItemPreview() {
    TaskItem(task = Task(name = "Sample Task", priority = 3, isImportant = true, isCompleted = false))
}