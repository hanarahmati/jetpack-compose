package co.fanavari.mycomposeapplication.screens.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val selected = mutableStateOf(savedStateHandle["initialSelected"] ?: false)
    private val items = listOf("Item 1", "Item 2", "Item 3", "Special Item")
    val filteredItems = mutableStateOf(items)

    val showDialog = mutableStateOf(false)

    init {
        filterItems()
    }

    fun onSelectedChange() {
        selected.value = !selected.value
        filterItems()
        savedStateHandle["initialSelected"] = selected.value
    }

    private fun filterItems(){
        filteredItems.value =  if (selected.value) {
            items.filter { it.contains("2") }
        } else {
            items
        }
    }

    fun onDialogShow() {
        showDialog.value = true
    }

    fun onDialogDismiss() {
        showDialog.value = false
    }

    fun onDialogConfirm() {
        showDialog.value = false
        // Handle additional confirmation logic if needed
    }
}