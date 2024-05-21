package co.fanavari.mycomposeapplication.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import co.fanavari.mycomposeapplication.screens.components.AddTaskDialog
import co.fanavari.mycomposeapplication.screens.components.TaskList
import co.fanavari.mycomposeapplication.viewmodel.TaskManagerViewModel

@Composable
fun TaskManagerScreen(
    navController: NavController,
    taskManagerViewModel: TaskManagerViewModel = viewModel()
    ){

    val showDialog by taskManagerViewModel.showDialog

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { taskManagerViewModel.onDialogShow() }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Task")
            }
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TaskList(tasks = taskManagerViewModel.tasks)
            }

            if (showDialog) {
                AddTaskDialog(onAdd = { task ->
                    taskManagerViewModel.addTask(task)
                    taskManagerViewModel.onDialogDismiss()
                }, onDismiss = { taskManagerViewModel.onDialogDismiss() })
            }
        }
    )


}

@Preview(showBackground = true)
@Composable
fun TaskManagerScreenPreview() {
    TaskManagerScreen(navController = rememberNavController())
}