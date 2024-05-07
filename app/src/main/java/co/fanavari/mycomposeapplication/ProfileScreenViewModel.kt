package co.fanavari.mycomposeapplication

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ProfileScreenViewModel(val initialSelected: Boolean = false) : ViewModel() {
    val selected = mutableStateOf(initialSelected)

    fun onSelectedChange() {
        selected.value = !selected.value
    }
}