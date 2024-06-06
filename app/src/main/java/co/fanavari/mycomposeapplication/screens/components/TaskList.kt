package co.fanavari.mycomposeapplication.screens.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import co.fanavari.mycomposeapplication.model.Task
import co.fanavari.mycomposeapplication.screens.taskManager.TaskManagerViewModel

@Composable
fun TaskList(tasks: List<Task>, viewModel: TaskManagerViewModel) {
    LazyColumn {
        items(tasks.size) { index ->
            TaskItem(task = tasks[index], viewModel= viewModel)
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun TaskListPreview() {
    val tasks = listOf(
        Task(id = "1", name = "Task 1", priority = 1, isImportant = false, isCompleted = true),
        Task(id = "2", name = "Task 2", priority = 2, isImportant = true, isCompleted = false),
        Task(id = "3", name = "Task 3", priority = 3, isImportant = false, isCompleted = false)
    )
    val viewModel = TaskManagerViewModel()
    TaskList(tasks = tasks, viewModel = viewModel)
}*/
