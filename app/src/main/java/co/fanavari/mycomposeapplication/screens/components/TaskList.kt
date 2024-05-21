package co.fanavari.mycomposeapplication.screens.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import co.fanavari.mycomposeapplication.model.Task

@Composable
fun TaskList(tasks: List<Task>) {
    LazyColumn {
        items(tasks.size) { index ->
            TaskItem(task = tasks[index])
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskListPreview() {
    val tasks = listOf(
        Task(name = "Task 1", priority = 1, isImportant = false, isCompleted = true),
        Task(name = "Task 2", priority = 2, isImportant = true, isCompleted = false),
        Task(name = "Task 3", priority = 3, isImportant = false, isCompleted = false)
    )
    TaskList(tasks = tasks)
}