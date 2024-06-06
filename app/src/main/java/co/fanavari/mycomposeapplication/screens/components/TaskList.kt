package co.fanavari.mycomposeapplication.screens.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import co.fanavari.mycomposeapplication.data.model.Task
import co.fanavari.mycomposeapplication.screens.taskManager.TaskManagerViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList

@Composable
fun TaskList(tasks: StateFlow<List<Task>>, viewModel: TaskManagerViewModel) {
    LazyColumn {
        items(tasks.value.size) { index ->
            TaskItem(task = tasks.value[index], viewModel= viewModel)
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
