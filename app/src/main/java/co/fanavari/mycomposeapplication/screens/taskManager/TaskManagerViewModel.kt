package co.fanavari.mycomposeapplication.screens.taskManager

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import co.fanavari.mycomposeapplication.model.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskManagerViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val tasks = mutableStateListOf<Task>()
    val showDialog = mutableStateOf(false)

    fun onDialogShow() {
        showDialog.value = true
    }

    fun onDialogDismiss() {
        showDialog.value = false
    }

    fun addTask(task: Task) {
        tasks.add(task)
    }

    fun updateTask(updatedTask: Task) {
        tasks.indexOfFirst { it.id == updatedTask.id }.takeIf { it != -1 }?.let { index ->
            tasks[index] = updatedTask
        }
    }

    fun deleteTask(task: Task) {
        tasks.remove(task)
    }
}