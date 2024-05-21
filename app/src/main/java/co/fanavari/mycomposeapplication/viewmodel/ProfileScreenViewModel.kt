package co.fanavari.mycomposeapplication.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ProfileScreenViewModel(val initialSelected: Boolean = false) : ViewModel() {
    val selected = mutableStateOf(initialSelected)
    private val items = listOf("Item 1", "Item 2", "Item 3", "Special Item")
    val filteredItems = mutableStateOf(items)

    val showDialog = mutableStateOf(false)

    fun onSelectedChange() {
        selected.value = !selected.value
        filterItems()
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